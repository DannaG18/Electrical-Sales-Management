package com.esms.purchase.infrastructure.adapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Properties;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

import com.esms.purchase.application.CreatePurchaseUC;
import com.esms.purchase.application.DeletePurchaseUC;
import com.esms.purchase.application.FindAllPurchaseUC;
import com.esms.purchase.application.FindPurchaseUC;
import com.esms.purchase.application.UpdatePurchaseUC;
import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;
import com.esms.purchase.infrastructure.repository.PurchaseRepository;
import com.esms.ui.CrudUi;

public class PurchaseAdapter extends JFrame{
    private final PurchaseService purchaseService;
    private final CreatePurchaseUC createPurchaseUC;
    private final UpdatePurchaseUC updatePurchaseUC;
    private final DeletePurchaseUC deletePurchaseUC;
    private final FindPurchaseUC findPurchaseUC;
    private final FindAllPurchaseUC findAllPurchaseUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public PurchaseAdapter() {
        this.purchaseService = new PurchaseRepository();
        this.createPurchaseUC = new CreatePurchaseUC(this.purchaseService);
        this.updatePurchaseUC = new UpdatePurchaseUC(this.purchaseService);
        this.deletePurchaseUC = new DeletePurchaseUC(this.purchaseService);
        this.findPurchaseUC = new FindPurchaseUC(this.purchaseService);
        this.findAllPurchaseUC = new FindAllPurchaseUC(this.purchaseService);
    
           // Configuración del JFrame
           ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
           // imagen
           setIconImage(windowIcon.getImage());
           setTitle("Purchase Menu");
           setSize(500, 600);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setLocationRelativeTo(null);
           setResizable(false);
   
           cardLayout = new CardLayout();
           mainPanel = new JPanel(cardLayout);
   
           // Panel principal con los botones del menú
           JPanel menuPanel = createMenuPanel();
           mainPanel.add(menuPanel, "Menu");
   
           // Paneles para cada operación
           JPanel addPanel = createOperationPanel("Add Purchase", "Add", createAddPanel());
           JPanel searchPanel = createOperationPanel("Search Purchase", "Search", createSearchPanel());
           JPanel findAllPanel = createOperationPanel("Find All Purchase", "Find All", createFindAllPanel());
           JPanel updatePanel = createOperationPanel("Update Purchase", "Update", createUpdatePanel());
           JPanel deletePanel = createOperationPanel("Delete Purchase", "Delete", createDeletePanel());
   
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
           JPanel headerPanel = createHeaderPanel("Purchase CRUD");
           panel.add(headerPanel, BorderLayout.NORTH);
   
           // Crear un panel para los botones con GridLayout
           JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
   
           // Añadir márgenes alrededor de los botones
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
   
           // Crear botones personalizados con esquinas redondeadas
           JButton addButton = createRoundedButton("Add Purchase");
           JButton searchButton = createRoundedButton("Search Purchase");
           JButton findAllButton = createRoundedButton("Find All Purchase");
           JButton updateButton = createRoundedButton("Update Purchase");
           JButton deleteButton = createRoundedButton("Delete Purchase");
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
   
       private JPanel createOperationPanel(String title, String cardTotalAmount, JPanel operationPanel) {
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
       
           JLabel supplierIdLabel = new JLabel("Enter supplierId:");
           JTextField supplierIdField = new JTextField(10);
           JLabel employeeIdLabel = new JLabel("Enter employeeId:");
           JTextField employeeIdField = new JTextField(10);
           JLabel puchaseDateLabel = new JLabel("Select puchaseDate:");
           
           // Configuración de JDatePicker
           UtilDateModel model = new UtilDateModel();
           Properties p = new Properties();
           p.put("text.today", "Today");
           p.put("text.month", "Month");
           p.put("text.year", "Year");
           JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
           JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
       
           JLabel totalAmountLabel = new JLabel("Enter totalAmount:");
           JTextField totalAmountField = new JTextField(10);
           JLabel branchIdLabel = new JLabel("Enter branchIdId:");
           JTextField branchIdField = new JTextField(10);
       
           JButton submitButton = createRoundedButton("Submit");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(puchaseDateLabel);
           formPanel.add(datePicker);
           formPanel.add(totalAmountLabel);
           formPanel.add(totalAmountField);
           formPanel.add(supplierIdLabel);
           formPanel.add(supplierIdField);
           formPanel.add(employeeIdLabel);
           formPanel.add(employeeIdField);
           formPanel.add(branchIdLabel);
           formPanel.add(branchIdField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
       
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                   if (selectedDate == null) {
                       throw new IllegalArgumentException("Movement date cannot be empty.");
                   }
                   String puchaseDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                   double totalAmount = Integer.parseInt(totalAmountField.getText().trim());
                   int supplierId = Integer.parseInt(supplierIdField.getText().trim());
                   int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                   int branchId = Integer.parseInt(branchIdField.getText().trim());

       
                   // Crear nuevo Purchase
                   Purchase purchase = new Purchase();
                   purchase.setSupplierId(supplierId);
                   purchase.setEmployeeId(employeeId);
                   purchase.setPuchaseDate(puchaseDate);
                   purchase.setTotalAmount(totalAmount);
                   purchase.setBranchId(branchId);
       
                   // Ejecutar caso de uso
                   createPurchaseUC.execute(purchase);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Purchase added successfully.");
       
                   // Limpiar los campos de texto
                   datePicker.getModel().setValue(null);
                   totalAmountField.setText("");
                   supplierIdField.setText("");
                   employeeIdField.setText("");
                   branchIdField.setText("");

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
       
           JLabel idLabel = new JLabel("Enter Purchase ID:");
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
                   findPurchaseUC.execute(id).ifPresentOrElse(
                           purchase -> showPurchaseDetails(purchase),
                           () -> JOptionPane.showMessageDialog(this, "Purchase not found.", "Error",
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
           // JPanel headerPanel = createHeaderPanel("Purchase");
   
           // Crear tabla para mostrar los datos
           String[] columnTotalAmounts = { "ID", "SupplierId", "EmployeeId", "PuchaseDate", "TotalAmount", "MovementType", "EmployeeId" };
           DefaultTableModel tableModel = new DefaultTableModel(columnTotalAmounts, 0); // Modelo de la tabla
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
               findAllPurchaseUC.execute().forEach(purchase -> {
                   Object[] row = { purchase.getId(), purchase.getPuchaseDate(), purchase.getTotalAmount(), purchase.getSupplierId(),
                           purchase.getEmployeeId(), purchase.getBranchId() };
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
           JLabel supplierIdLabel = new JLabel("Enter supplierId:");
           JTextField supplierIdField = new JTextField(10);
           JLabel employeeIdLabel = new JLabel("Enter employeeId:");
           JTextField employeeIdField = new JTextField(10);
           JLabel puchaseDateLabel = new JLabel("Select puchaseDate:");
           
           // Configuración de JDatePicker
           UtilDateModel model = new UtilDateModel();
           Properties p = new Properties();
           p.put("text.today", "Today");
           p.put("text.month", "Month");
           p.put("text.year", "Year");
           JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
           JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
       
           JLabel totalAmountLabel = new JLabel("Enter totalAmount:");
           JTextField totalAmountField = new JTextField(10);
           JLabel branchIdLabel = new JLabel("Enter branchIdId:");
           JTextField branchIdField = new JTextField(10);
       
           JButton submitButton = createRoundedButton("Submit");
           JButton backButton = createRoundedButton("Back");
       
           formPanel.add(idLabel);
           formPanel.add(idField);
           formPanel.add(puchaseDateLabel);
           formPanel.add(datePicker);
           formPanel.add(totalAmountLabel);
           formPanel.add(totalAmountField);
           formPanel.add(supplierIdLabel);
           formPanel.add(supplierIdField);
           formPanel.add(employeeIdLabel);
           formPanel.add(employeeIdField);
           formPanel.add(branchIdLabel);
           formPanel.add(branchIdField);
           formPanel.add(submitButton);
           formPanel.add(backButton);
       
           
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
           marginPanel.add(formPanel, BorderLayout.CENTER);
           
           panel.add(marginPanel, BorderLayout.CENTER);
       
           submitButton.addActionListener(e -> {
               try {
                   int id = Integer.parseInt(idField.getText().trim());
                   java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                   if (selectedDate == null) {
                       throw new IllegalArgumentException("Movement date cannot be empty.");
                   }
                   String puchaseDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                   double totalAmount = Integer.parseInt(totalAmountField.getText().trim());
                   int supplierId = Integer.parseInt(supplierIdField.getText().trim());
                   int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                   int branchId = Integer.parseInt(branchIdField.getText().trim());

                   // Actualizar Purchase
                   Purchase purchase = new Purchase();
                   purchase.setId(id);
                   purchase.setSupplierId(supplierId);
                   purchase.setEmployeeId(employeeId);
                   purchase.setPuchaseDate(puchaseDate);
                   purchase.setTotalAmount(totalAmount);
                   purchase.setBranchId(branchId);
       
                   // Ejecutar caso de uso
                   updatePurchaseUC.execute(purchase);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Purchase updated successfully.");
       
                   // Limpiar los campos de texto
                   idField.setText("");
                   datePicker.getModel().setValue(null);
                   totalAmountField.setText("");
                   supplierIdField.setText("");
                   employeeIdField.setText("");
                   branchIdField.setText("");

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
       
       // Clase auxiliar para formatear la fecha
       public class DateLabelFormatter extends AbstractFormatter {
       
           private String datePattern = "yyyy-MM-dd";
           private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
       
           @Override
           public Object stringToValue(String text) throws ParseException {
               return dateFormatter.parseObject(text);
           }
       
           @Override
           public String valueToString(Object value) throws ParseException {
               if (value != null) {
                   Calendar cal = (Calendar) value;
                   return dateFormatter.format(cal.getTime());
               }
               return "";
           }
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
                   deletePurchaseUC.execute(id);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Purchase deleted successfully.");
       
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
       
   
       private void showPurchaseDetails(Purchase purchase) {
           String details = String.format("""
                   Purchase found:
                   ID: %d
                   PuchaseDate: %s
                   TotalAmount: %f
                   SupplierId: %d
                   EmployeeId: %d
                   BranchId: %d
   
                   """, purchase.getId(), purchase.getPuchaseDate(), purchase.getTotalAmount(), purchase.getSupplierId(),
                   purchase.getEmployeeId(), purchase.getBranchId() );
           JOptionPane.showMessageDialog(this, details, "Purchase Details", JOptionPane.INFORMATION_MESSAGE);
       }
   
       public static void main(String[] args) {
           new PurchaseAdapter();
       }
   
   }
