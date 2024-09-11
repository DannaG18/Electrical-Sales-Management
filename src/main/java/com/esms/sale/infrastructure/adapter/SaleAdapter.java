package com.esms.sale.infrastructure.adapter;

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

import com.esms.sale.application.CreateSaleUC;
import com.esms.sale.application.DeleteSaleUC;
import com.esms.sale.application.FindAllSaleUC;
import com.esms.sale.application.FindSaleUC;
import com.esms.sale.application.UpdateSaleUC;
import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;
import com.esms.sale.infrastructure.repository.SaleRepository;
import com.esms.ui.CrudUi;

public class SaleAdapter extends JFrame {
    private final SaleService saleService;
    private final CreateSaleUC createSaleUC;
    private final UpdateSaleUC updateSaleUC;
    private final DeleteSaleUC deleteSaleUC;
    private final FindSaleUC findSaleUC;
    private final FindAllSaleUC findAllSaleUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public SaleAdapter() {
        this.saleService = new SaleRepository();
        this.createSaleUC = new CreateSaleUC(saleService);
        this.updateSaleUC = new UpdateSaleUC(saleService);
        this.deleteSaleUC = new DeleteSaleUC(saleService);
        this.findSaleUC = new FindSaleUC(saleService);
        this.findAllSaleUC = new FindAllSaleUC(saleService);

           // Configuración del JFrame
           ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
           // imagen
           setIconImage(windowIcon.getImage());
           setTitle("Sale Menu");
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
           JPanel addPanel = createOperationPanel("Add Sale", "Add", createAddPanel());
           JPanel searchPanel = createOperationPanel("Search Sale", "Search", createSearchPanel());
           JPanel findAllPanel = createOperationPanel("Sales Report", "Find All", createFindAllPanel());
           JPanel updatePanel = createOperationPanel("Update Sale", "Update", createUpdatePanel());
           JPanel deletePanel = createOperationPanel("Delete Sale", "Delete", createDeletePanel());
   
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
           JPanel headerPanel = createHeaderPanel("Sale CRUD");
           panel.add(headerPanel, BorderLayout.NORTH);
   
           // Crear un panel para los botones con GridLayout
           JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
   
           // Añadir márgenes alrededor de los botones
           JPanel marginPanel = new JPanel(new BorderLayout());
           marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
   
           // Crear botones personalizados con esquinas redondeadas
           JButton addButton = createRoundedButton("Add Sale");
           JButton searchButton = createRoundedButton("Search Sale");
           JButton findAllButton = createRoundedButton("Sales Report");
           JButton updateButton = createRoundedButton("Update Sale");
           JButton deleteButton = createRoundedButton("Delete Sale");
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
       
           JLabel customerIdLabel = new JLabel("Enter customerId:");
           JTextField customerIdField = new JTextField(10);
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
           formPanel.add(customerIdLabel);
           formPanel.add(customerIdField);
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
                   int customerId = Integer.parseInt(customerIdField.getText().trim());
                   int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                   int branchId = Integer.parseInt(branchIdField.getText().trim());

       
                   // Crear nuevo Sale
                   Sale sale = new Sale();
                   sale.setCustomerId(customerId);
                   sale.setEmployeeId(employeeId);
                   sale.setSaleDate(puchaseDate);
                   sale.setTotalAmount(totalAmount);
                   sale.setBranchId(branchId);
       
                   // Ejecutar caso de uso
                   createSaleUC.execute(sale);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Sale added successfully.");
       
                   // Limpiar los campos de texto
                   datePicker.getModel().setValue(null);
                   totalAmountField.setText("");
                   customerIdField.setText("");
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
       
           JLabel idLabel = new JLabel("Enter Sale ID:");
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
                   findSaleUC.execute(id).ifPresentOrElse(
                           sale -> showSaleDetails(sale),
                           () -> JOptionPane.showMessageDialog(this, "Sale not found.", "Error",
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
           // JPanel headerPanel = createHeaderPanel("Sale");
   
           // Crear tabla para mostrar los datos
           String[] columnTotalAmounts = { "ID", "CustomerId", "EmployeeId", "SaleDate", "TotalAmount", "MovementType", "EmployeeId" };
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
               findAllSaleUC.execute().forEach(sale -> {
                   Object[] row = { sale.getId(), sale.getSaleDate(), sale.getTotalAmount(), sale.getCustomerId(),
                           sale.getEmployeeId(), sale.getBranchId() };
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
           JLabel customerIdLabel = new JLabel("Enter customerId:");
           JTextField customerIdField = new JTextField(10);
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
           formPanel.add(customerIdLabel);
           formPanel.add(customerIdField);
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
                   int customerId = Integer.parseInt(customerIdField.getText().trim());
                   int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                   int branchId = Integer.parseInt(branchIdField.getText().trim());

                   // Actualizar Sale
                   Sale sale = new Sale();
                   sale.setId(id);
                   sale.setCustomerId(customerId);
                   sale.setEmployeeId(employeeId);
                   sale.setSaleDate(puchaseDate);
                   sale.setTotalAmount(totalAmount);
                   sale.setBranchId(branchId);
       
                   // Ejecutar caso de uso
                   updateSaleUC.execute(sale);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Sale updated successfully.");
       
                   // Limpiar los campos de texto
                   idField.setText("");
                   datePicker.getModel().setValue(null);
                   totalAmountField.setText("");
                   customerIdField.setText("");
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
                   deleteSaleUC.execute(id);
       
                   // Mostrar mensaje de éxito
                   JOptionPane.showMessageDialog(this, "Sale deleted successfully.");
       
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
       
   
       private void showSaleDetails(Sale sale) {
           String details = String.format("""
                   Sale found:
                   ID: %d
                   SaleDate: %s
                   TotalAmount: %f
                   CustomerId: %d
                   EmployeeId: %d
                   BranchId: %d
   
                   """, sale.getId(), sale.getSaleDate(), sale.getTotalAmount(), sale.getCustomerId(),
                   sale.getEmployeeId(), sale.getBranchId() );
           JOptionPane.showMessageDialog(this, details, "Sale Details", JOptionPane.INFORMATION_MESSAGE);
       }
   
       public static void main(String[] args) {
           new SaleAdapter();
       }
   
   }
