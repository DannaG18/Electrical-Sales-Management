package com.esms.purchase_details.infrastructure.adapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.esms.purchase_details.application.CreatePurchaseDetailsUC;
import com.esms.purchase_details.application.DeletePurchaseDetailsUC;
import com.esms.purchase_details.application.FindAllPurchaseDetailsUC;
import com.esms.purchase_details.application.FindPurchaseDetailsUC;
import com.esms.purchase_details.application.UpdatePurchaseDetailsUC;
import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;
import com.esms.purchase_details.infrastructure.repository.PurchaseDetailsRepository;
import com.esms.ui.CrudUi;

public class PurchaseDetailsAdapter extends JFrame{
    private final PurchaseDetailsService purchaseDetailsService;
    private final CreatePurchaseDetailsUC createPurchaseDetailsUC;
    private final UpdatePurchaseDetailsUC updatePurchaseDetailsUC;
    private final DeletePurchaseDetailsUC deletePurchaseDetailsUC;
    private final FindPurchaseDetailsUC findPurchaseDetailsUC;
    private final FindAllPurchaseDetailsUC findAllPurchaseDetailsUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

      private CrudUi crudUi;

    public PurchaseDetailsAdapter() {
        this.purchaseDetailsService = new PurchaseDetailsRepository();
        this.createPurchaseDetailsUC = new CreatePurchaseDetailsUC(purchaseDetailsService);
        this.updatePurchaseDetailsUC = new UpdatePurchaseDetailsUC(purchaseDetailsService);
        this.deletePurchaseDetailsUC = new DeletePurchaseDetailsUC(purchaseDetailsService);
        this.findPurchaseDetailsUC = new FindPurchaseDetailsUC(purchaseDetailsService);
        this.findAllPurchaseDetailsUC = new FindAllPurchaseDetailsUC(purchaseDetailsService);


           // Configuración del JFrame
           ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
           // imagen
           setIconImage(windowIcon.getImage());
           setTitle("PurchaseDetails Menu");
           setSize(400, 500);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setLocationRelativeTo(null);
           setResizable(false);
   
           cardLayout = new CardLayout();
           mainPanel = new JPanel(cardLayout);
   
           // Panel principal con los botones del menú
           JPanel menuPanel = createMenuPanel();
           mainPanel.add(menuPanel, "Menu");
   
           // Paneles para cada operación
           JPanel addPanel = createOperationPanel("Add PurchaseDetails", "Add", createAddPanel());
           JPanel searchPanel = createOperationPanel("Search PurchaseDetails", "Search", createSearchPanel());
           JPanel findAllPanel = createOperationPanel("Find All PurchaseDetails", "Find All", createFindAllPanel());
           JPanel updatePanel = createOperationPanel("Update PurchaseDetails", "Update", createUpdatePanel());
           JPanel deletePanel = createOperationPanel("Delete PurchaseDetails", "Delete", createDeletePanel());
   
           // Añadir los paneles al CardLayout
           mainPanel.add(addPanel, "Add");
           mainPanel.add(searchPanel, "Search");
           mainPanel.add(findAllPanel, "FindAll");
           mainPanel.add(updatePanel, "Update");
           mainPanel.add(deletePanel, "Delete");
   
           // Añadir el panel principal al JFrame
           add(mainPanel);
   
           // Mostrar el menú inicial
           cardLayout.show(mainPanel, "Menu");
   
           // Hacer visible la ventana
           setVisible(true);
       }
   
       private JPanel createHeaderPanel(String title) {
           JPanel headerPanel = new JPanel(new BorderLayout());
   
           JLabel titleLabel = new JLabel(title);
           titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
           titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
   
           // Cargar una imagen pequeña
           ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png");
           JLabel imageLabel = new JLabel(icon);
   
           // Añadir espacio debajo del encabezado
           headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
   
           headerPanel.add(titleLabel, BorderLayout.CENTER);
           headerPanel.add(imageLabel, BorderLayout.EAST);
   
           return headerPanel;
       }
   
       private JPanel createMenuPanel() {
           JPanel panel = new JPanel(new BorderLayout());
   
           // Crear encabezado solo para el menú
           JPanel headerPanel = createHeaderPanel("PurchaseDetails CRUD");
           panel.add(headerPanel, BorderLayout.NORTH);
   
           // Crear un panel para los botones con GridLayout
           JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
   
           // Añadir márgenes alrededor de los botones
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
   
           // Crear botones personalizados con esquinas redondeadas
           JButton addButton = createRoundedButton("Add PurchaseDetails");
           JButton searchButton = createRoundedButton("Search PurchaseDetails");
           JButton findAllButton = createRoundedButton("Find All PurchaseDetails");
           JButton updateButton = createRoundedButton("Update PurchaseDetails");
           JButton deleteButton = createRoundedButton("Delete PurchaseDetails");
           JButton exitButton = createRoundedButton("Back");
   
           buttonPanel.add(addButton);
           buttonPanel.add(searchButton);
           buttonPanel.add(findAllButton);
           buttonPanel.add(updateButton);
           buttonPanel.add(deleteButton);
           buttonPanel.add(exitButton);
   
           marginPanel.add(buttonPanel, BorderLayout.CENTER);
           panel.add(marginPanel, BorderLayout.CENTER);
   
           // Action Listeners para cada botón
           addButton.addActionListener(e -> cardLayout.show(mainPanel, "Add"));
           searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
           findAllButton.addActionListener(e -> cardLayout.show(mainPanel, "FindAll"));
           updateButton.addActionListener(e -> cardLayout.show(mainPanel, "Update"));
           deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "Delete"));
           exitButton.addActionListener(e -> { this.dispose(); crudUi.setVisible(true); }); 
   
           return panel;
       }
   
       private JPanel createOperationPanel(String title, String cardUnitPrice, JPanel operationPanel) {
           JPanel panel = new JPanel(new BorderLayout());
   
           // Crear encabezado solo para operaciones
           JPanel headerPanel = createHeaderPanel(title);
           panel.add(headerPanel, BorderLayout.NORTH);
   
           panel.add(operationPanel, BorderLayout.CENTER);
   
           return panel;
       }
   
       private JButton createRoundedButton(String text) {
           JButton button = new JButton(text) {
               @Override
               protected void paintComponent(Graphics g) {
                   Graphics2D g2 = (Graphics2D) g.create();
                   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                   g2.setColor(getBackground());
                   g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                   super.paintComponent(g2);
                   g2.dispose();
               }
   
               @Override
               protected void paintBorder(Graphics g) {
                   Graphics2D g2 = (Graphics2D) g.create();
                   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                   g2.setColor(getForeground());
                   g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                   g2.dispose();
               }
   
               @Override
               public void setContentAreaFilled(boolean b) {
                   super.setContentAreaFilled(false);
               }
           };
           button.setFocusPainted(false);
           return button;
       }
   
       private JPanel createAddPanel() {
           JPanel panel = new JPanel(new BorderLayout());
       
           JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
       
           JLabel purchaseIdLabel = new JLabel("Enter purchaseId:");
           JTextField purchaseIdField = new JTextField(10);
           JLabel productIdLabel = new JLabel("Enter productId:");
           JTextField productIdField = new JTextField(10);
       
           JLabel quantityLabel = new JLabel("Enter quantityId:");
           JTextField quantityField = new JTextField(10);
           JLabel unitPriceLabel = new JLabel("Enter unitPrice:");
           JTextField unitPriceField = new JTextField(10);
       
           JButton submitButton = createRoundedButton("Submit");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(purchaseIdLabel);
           formPanel.add(purchaseIdField);
           formPanel.add(productIdLabel);
           formPanel.add(productIdField);
           formPanel.add(quantityLabel);
           formPanel.add(quantityField);
           formPanel.add(unitPriceLabel);
           formPanel.add(unitPriceField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
       
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   int purchaseId = Integer.parseInt(purchaseIdField.getText().trim());
                   int productId = Integer.parseInt(productIdField.getText().trim());
                   int quantity = Integer.parseInt(quantityField.getText().trim());
                   double unitPrice = Integer.parseInt(unitPriceField.getText().trim());

       
                   // Crear nuevo PurchaseDetails
                   PurchaseDetails purchaseDetails = new PurchaseDetails();
                   purchaseDetails.setPurchaseId(purchaseId);
                   purchaseDetails.setProductId(productId);

                   purchaseDetails.setQuantity(quantity);
                   purchaseDetails.setUnitPrice(unitPrice);
       
                   // Ejecutar caso de uso
                   createPurchaseDetailsUC.execute(purchaseDetails);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "PurchaseDetails added successfully.");
       
                   // Limpiar los campos de texto

                   purchaseIdField.setText("");
                   productIdField.setText("");
                   quantityField.setText("");
                   unitPriceField.setText("");

               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error",
                           JOptionPane.ERROR_MESSAGE);
               } catch (IllegalArgumentException ex) {
                   JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           });
       
           backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
       
           return panel;
       }
       
       
   
       private JPanel createSearchPanel() {
           JPanel panel = new JPanel(new BorderLayout());
       
           JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
       
           JLabel idLabel = new JLabel("Enter PurchaseDetails ID:");
           JTextField idField = new JTextField(5);
           idField.setPreferredSize(new Dimension(300, 250));
       
           JButton submitButton = createRoundedButton("Search");
           submitButton.setPreferredSize(new Dimension(100, 30));
       
           JButton backButton = createRoundedButton("Back");
           backButton.setPreferredSize(new Dimension(100, 30));
       
           formPanel.add(idLabel);
           formPanel.add(idField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
       
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   int id = Integer.parseInt(idField.getText().trim());
                   findPurchaseDetailsUC.execute(id).ifPresentOrElse(
                           purchaseDetails -> showPurchaseDetailsDetails(purchaseDetails),
                           () -> JOptionPane.showMessageDialog(this, "PurchaseDetails not found.", "Error",
                                   JOptionPane.ERROR_MESSAGE));
                   idField.setText(""); // Limpiar el campo de texto
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
                           JOptionPane.ERROR_MESSAGE);
               }
           });
       
           backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
       
           return panel;
       }
       
   
       private JPanel createFindAllPanel() {
           JPanel panel = new JPanel(new BorderLayout());
   
           // Crear encabezado del panel
           // JPanel headerPanel = createHeaderPanel("PurchaseDetails");
   
           // Crear tabla para mostrar los datos
           String[] columnUnitPrices = { "ID", "PurchaseId", "ProductId", "PuchaseDate", "UnitPrice", "MovementType", "ProductId" };
           DefaultTableModel tableModel = new DefaultTableModel(columnUnitPrices, 0); // Modelo de la tabla
           JTable table = new JTable(tableModel);
           JScrollPane scrollPane = new JScrollPane(table);
   
           // Añadir la tabla al panel
           // panel.add(headerPanel, BorderLayout.NORTH);
           panel.add(scrollPane, BorderLayout.CENTER);
   
           JButton searchButton = createRoundedButton("Search");
           searchButton.addActionListener(e -> {
               // Limpiar el modelo de la tabla antes de agregar nuevos datos
               tableModel.setRowCount(0);
       
               // Obtiene todos los productos y los añade a la tabla
               findAllPurchaseDetailsUC.execute().forEach(purchaseDetails -> {
                   Object[] row = { purchaseDetails.getId(), purchaseDetails.getPurchaseId(),
                           purchaseDetails.getProductId(), purchaseDetails.getQuantity(), purchaseDetails.getUnitPrice(), };
                   tableModel.addRow(row);
               });
           });
   
           JButton backButton = createRoundedButton("Back");
           backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
   
           // Panel para el botón
           JPanel buttonPanel = new JPanel();
           buttonPanel.add(backButton);
           buttonPanel.add(searchButton);
   
           // Añadir el panel del botón debajo de la tabla
           panel.add(buttonPanel, BorderLayout.SOUTH);
   
           tableModel.setRowCount(0);
   
           return panel;
       }
       
       private JPanel createUpdatePanel() {
           JPanel panel = new JPanel(new BorderLayout());
       
           JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
       
           JLabel idLabel = new JLabel("Enter ID:");
           JTextField idField = new JTextField(20);
           JLabel purchaseIdLabel = new JLabel("Enter purchaseId:");
           JTextField purchaseIdField = new JTextField(10);
           JLabel productIdLabel = new JLabel("Enter productId:");
           JTextField productIdField = new JTextField(10);
       
           JLabel quantityLabel = new JLabel("Enter quantityId:");
           JTextField quantityField = new JTextField(10);
           JLabel unitPriceLabel = new JLabel("Enter unitPrice:");
           JTextField unitPriceField = new JTextField(10);
       
           JButton submitButton = createRoundedButton("Submit");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(idLabel);
           formPanel.add(idField);
           formPanel.add(purchaseIdLabel);
           formPanel.add(purchaseIdField);
           formPanel.add(productIdLabel);
           formPanel.add(productIdField);
           formPanel.add(quantityLabel);
           formPanel.add(quantityField);
           formPanel.add(unitPriceLabel);
           formPanel.add(unitPriceField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
           
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   int id = Integer.parseInt(idField.getText().trim());
                   int purchaseId = Integer.parseInt(purchaseIdField.getText().trim());
                   int productId = Integer.parseInt(productIdField.getText().trim());
                   int quantity = Integer.parseInt(quantityField.getText().trim());
                   double unitPrice = Integer.parseInt(unitPriceField.getText().trim());

                   // Actualizar PurchaseDetails
                   PurchaseDetails purchaseDetails = new PurchaseDetails();
                   purchaseDetails.setId(id);
                   purchaseDetails.setPurchaseId(purchaseId);
                   purchaseDetails.setProductId(productId);

                   purchaseDetails.setQuantity(quantity);
                   purchaseDetails.setUnitPrice(unitPrice);
       
                   // Ejecutar caso de uso
                   updatePurchaseDetailsUC.execute(purchaseDetails);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "PurchaseDetails updated successfully.");
       
                   // Limpiar los campos de texto
                   idField.setText("");

                   purchaseIdField.setText("");
                   productIdField.setText("");
                   quantityField.setText("");
                   unitPriceField.setText("");

               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error",
                           JOptionPane.ERROR_MESSAGE);
               } catch (IllegalArgumentException ex) {
                   JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           });
       
           backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
       
           return panel;
       }
       
   
       private JPanel createDeletePanel() {
           JPanel panel = new JPanel(new BorderLayout());
       
           JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
       
           JLabel idLabel = new JLabel("Enter ID to delete:");
           JTextField idField = new JTextField(20);
       
           JButton submitButton = createRoundedButton("Delete");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(idLabel);
           formPanel.add(idField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
       
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   int id = Integer.parseInt(idField.getText().trim());
       
                   // Ejecutar caso de uso
                   deletePurchaseDetailsUC.execute(id);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "PurchaseDetails deleted successfully.");
       
                   // Limpiar el campo de texto
                   idField.setText("");
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
                           JOptionPane.ERROR_MESSAGE);
               }
           });
       
           backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
       
           return panel;
       }
       
   
       private void showPurchaseDetailsDetails(PurchaseDetails purchaseDetails) {
           String details = String.format("""
                   PurchaseDetails found:
                   ID: %d
                   PurchaseId: %d
                   ProductId: %d
                   Quantity: %d
                   UnitPrice: %f
   
                   """, purchaseDetails.getId(), purchaseDetails.getPurchaseId(),
                   purchaseDetails.getProductId(), purchaseDetails.getQuantity(), purchaseDetails.getUnitPrice() );
           JOptionPane.showMessageDialog(this, details, "PurchaseDetails Details", JOptionPane.INFORMATION_MESSAGE);
       }
   
       public static void main(String[] args) {
           new PurchaseDetailsAdapter();
       }
   
   }
