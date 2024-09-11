package com.esms.sale_details.infrastructure.adapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.esms.sale_details.application.CreateSaleDetailsUC;
import com.esms.sale_details.application.DeleteSaleDetailsUC;
import com.esms.sale_details.application.FindAllSaleDetailsUC;
import com.esms.sale_details.application.FindSaleDetailsUC;
import com.esms.sale_details.application.UpdateSaleDetailsUC;
import com.esms.sale_details.domain.entity.SaleDetails;
import com.esms.sale_details.domain.service.SaleDetailsService;
import com.esms.sale_details.infrastructure.repository.SaleDetailsRepository;
import com.esms.ui.CrudUi;

public class SalesDetailsAdapter extends JFrame{
    private final SaleDetailsService saleDetailsService;
    private final CreateSaleDetailsUC  createSaleDetailsUC;
    private final UpdateSaleDetailsUC updateSaleDetailsUC;
    private final DeleteSaleDetailsUC deleteSaleDetailsUC;
    private final FindSaleDetailsUC findSaleDetailsUC;
    private final FindAllSaleDetailsUC findAllSaleDetailsUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public SalesDetailsAdapter() {
        this.saleDetailsService = new SaleDetailsRepository();
        this.createSaleDetailsUC = new CreateSaleDetailsUC(saleDetailsService);
        this.updateSaleDetailsUC = new UpdateSaleDetailsUC(saleDetailsService);
        this.deleteSaleDetailsUC = new DeleteSaleDetailsUC(saleDetailsService);
        this.findSaleDetailsUC = new FindSaleDetailsUC(saleDetailsService);
        this.findAllSaleDetailsUC = new FindAllSaleDetailsUC(saleDetailsService);

           // Configuración del JFrame
           ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
           // imagen
           setIconImage(windowIcon.getImage());
           setTitle("SaleDetails Menu");
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
           JPanel addPanel = createOperationPanel("Add SaleDetails", "Add", createAddPanel());
           JPanel searchPanel = createOperationPanel("Search SaleDetails", "Search", createSearchPanel());
           JPanel findAllPanel = createOperationPanel("Find All SaleDetails", "Find All", createFindAllPanel());
           JPanel updatePanel = createOperationPanel("Update SaleDetails", "Update", createUpdatePanel());
           JPanel deletePanel = createOperationPanel("Delete SaleDetails", "Delete", createDeletePanel());
   
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
           JPanel headerPanel = createHeaderPanel("SaleDetails CRUD");
           panel.add(headerPanel, BorderLayout.NORTH);
   
           // Crear un panel para los botones con GridLayout
           JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
   
           // Añadir márgenes alrededor de los botones
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
   
           // Crear botones personalizados con esquinas redondeadas
           JButton addButton = createRoundedButton("Add SaleDetails");
           JButton searchButton = createRoundedButton("Search SaleDetails");
           JButton findAllButton = createRoundedButton("Find All SaleDetails");
           JButton updateButton = createRoundedButton("Update SaleDetails");
           JButton deleteButton = createRoundedButton("Delete SaleDetails");
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
       
           JLabel saleIdLabel = new JLabel("Enter saleId:");
           JTextField saleIdField = new JTextField(10);
           JLabel productIdLabel = new JLabel("Enter productId:");
           JTextField productIdField = new JTextField(10);
       
           JLabel quantityLabel = new JLabel("Enter quantityId:");
           JTextField quantityField = new JTextField(10);
           JLabel unitPriceLabel = new JLabel("Enter unitPrice:");
           JTextField unitPriceField = new JTextField(10);
       
           JButton submitButton = createRoundedButton("Submit");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(saleIdLabel);
           formPanel.add(saleIdField);
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
                   int saleId = Integer.parseInt(saleIdField.getText().trim());
                   int productId = Integer.parseInt(productIdField.getText().trim());
                   int quantity = Integer.parseInt(quantityField.getText().trim());
                   double unitPrice = Integer.parseInt(unitPriceField.getText().trim());

       
                   // Crear nuevo SaleDetails
                   SaleDetails saleDetails = new SaleDetails();
                   saleDetails.setSaleId(saleId);
                   saleDetails.setProductId(productId);

                   saleDetails.setQuantity(quantity);
                   saleDetails.setUnitPrice(unitPrice);
       
                   // Ejecutar caso de uso
                   createSaleDetailsUC.execute(saleDetails);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "SaleDetails added successfully.");
       
                   // Limpiar los campos de texto

                   saleIdField.setText("");
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
       
           JLabel idLabel = new JLabel("Enter SaleDetails ID:");
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
                   findSaleDetailsUC.execute(id).ifPresentOrElse(
                           saleDetails -> showSaleDetailsDetails(saleDetails),
                           () -> JOptionPane.showMessageDialog(this, "SaleDetails not found.", "Error",
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
           // JPanel headerPanel = createHeaderPanel("SaleDetails");
   
           // Crear tabla para mostrar los datos
           String[] columnUnitPrices = { "ID", "SaleId", "ProductId", "PuchaseDate", "UnitPrice", "MovementType", "ProductId" };
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
               findAllSaleDetailsUC.execute().forEach(saleDetails -> {
                   Object[] row = { saleDetails.getId(), saleDetails.getSaleId(),
                           saleDetails.getProductId(), saleDetails.getQuantity(), saleDetails.getUnitPrice(), };
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
           JLabel saleIdLabel = new JLabel("Enter saleId:");
           JTextField saleIdField = new JTextField(10);
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
           formPanel.add(saleIdLabel);
           formPanel.add(saleIdField);
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
                   int saleId = Integer.parseInt(saleIdField.getText().trim());
                   int productId = Integer.parseInt(productIdField.getText().trim());
                   int quantity = Integer.parseInt(quantityField.getText().trim());
                   double unitPrice = Integer.parseInt(unitPriceField.getText().trim());

                   // Actualizar SaleDetails
                   SaleDetails saleDetails = new SaleDetails();
                   saleDetails.setId(id);
                   saleDetails.setSaleId(saleId);
                   saleDetails.setProductId(productId);

                   saleDetails.setQuantity(quantity);
                   saleDetails.setUnitPrice(unitPrice);
       
                   // Ejecutar caso de uso
                   updateSaleDetailsUC.execute(saleDetails);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "SaleDetails updated successfully.");
       
                   // Limpiar los campos de texto
                   idField.setText("");

                   saleIdField.setText("");
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
                   deleteSaleDetailsUC.execute(id);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "SaleDetails deleted successfully.");
       
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
       
   
       private void showSaleDetailsDetails(SaleDetails saleDetails) {
           String details = String.format("""
                   SaleDetails found:
                   ID: %d
                   SaleId: %d
                   ProductId: %d
                   Quantity: %d
                   UnitPrice: %f
   
                   """, saleDetails.getId(), saleDetails.getSaleId(),
                   saleDetails.getProductId(), saleDetails.getQuantity(), saleDetails.getUnitPrice() );
           JOptionPane.showMessageDialog(this, details, "SaleDetails Details", JOptionPane.INFORMATION_MESSAGE);
       }
   
       public static void main(String[] args) {
           new SalesDetailsAdapter();
       }
   
   }
