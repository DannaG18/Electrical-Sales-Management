package com.esms.inventory_movements.infrastructure.adapter;

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

import com.esms.inventory_movements.application.CreateInventoryMovementsUC;
import com.esms.inventory_movements.application.DeleteInventoryMovementsUC;
import com.esms.inventory_movements.application.FindAllInventoryMovementsUC;
import com.esms.inventory_movements.application.FindInventoryMovementsUC;
import com.esms.inventory_movements.application.UpdateInventoryMovementsUC;
import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;
import com.esms.inventory_movements.infrastructure.repository.InventoryMovementsRepository;
import com.esms.ui.CrudUi;

public class InventoryMovementsAdapter extends JFrame {
    private final InventoryMovementsService inventoryMovementsService;
    private final CreateInventoryMovementsUC createInventoryMovementsUC;
    private final UpdateInventoryMovementsUC updateInventoryMovementsUC;
    private final DeleteInventoryMovementsUC deleteInventoryMovementsUC;
    private final FindAllInventoryMovementsUC findAllInventoryMovementsUC;
    private final FindInventoryMovementsUC findInventoryMovementsUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public InventoryMovementsAdapter() {
        this.inventoryMovementsService = new InventoryMovementsRepository();
        this.createInventoryMovementsUC = new CreateInventoryMovementsUC(inventoryMovementsService);
        this.updateInventoryMovementsUC = new UpdateInventoryMovementsUC(inventoryMovementsService);
        this.deleteInventoryMovementsUC = new DeleteInventoryMovementsUC(inventoryMovementsService);
        this.findAllInventoryMovementsUC = new FindAllInventoryMovementsUC(inventoryMovementsService);
        this.findInventoryMovementsUC = new FindInventoryMovementsUC(inventoryMovementsService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
        // imagen
        setIconImage(windowIcon.getImage());
        setTitle("InventoryMovements Menu");
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
        JPanel addPanel = createOperationPanel("Add InventoryMovements", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search InventoryMovements", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All InventoryMovements", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update InventoryMovements", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete InventoryMovements", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("InventoryMovements CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add InventoryMovements");
        JButton searchButton = createRoundedButton("Search InventoryMovements");
        JButton findAllButton = createRoundedButton("Find All InventoryMovements");
        JButton updateButton = createRoundedButton("Update InventoryMovements");
        JButton deleteButton = createRoundedButton("Delete InventoryMovements");
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

    private JPanel createOperationPanel(String title, String cardQuantity, JPanel operationPanel) {
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
    
        JPanel formPanel = new JPanel(new GridLayout(8, 5, 10, 10));
    
        JLabel productIdLabel = new JLabel("Enter productId:");
        JTextField productIdField = new JTextField(10);
        JLabel warehouseIdLabel = new JLabel("Enter warehouseId:");
        JTextField warehouseIdField = new JTextField(10);
        JLabel movementDateLabel = new JLabel("Select movementDate:");
        
        // Configuración de JDatePicker
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    
        JLabel quantityLabel = new JLabel("Enter quantity:");
        JTextField quantityField = new JTextField(10);
        JLabel movementTypeLabel = new JLabel("Enter movementType:");
        JTextField movementTypeField = new JTextField(10);
        JLabel employeeIdLabel = new JLabel("Enter employeeId:");
        JTextField employeeIdField = new JTextField(10);
    
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(warehouseIdLabel);
        formPanel.add(warehouseIdField);
        formPanel.add(movementDateLabel);
        formPanel.add(datePicker);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(movementTypeLabel);
        formPanel.add(movementTypeField);
        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int productId = Integer.parseInt(productIdField.getText().trim());
                int warehouseId = Integer.parseInt(warehouseIdField.getText().trim());
                java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                if (selectedDate == null) {
                    throw new IllegalArgumentException("Movement date cannot be empty.");
                }
                String movementDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                int quantity = Integer.parseInt(quantityField.getText().trim());
                String movementType = movementTypeField.getText().trim();
                int employeeId = Integer.parseInt(employeeIdField.getText().trim());
    
                // Crear nuevo InventoryMovements
                InventoryMovements inventoryMovements = new InventoryMovements();
                inventoryMovements.setProductId(productId);
                inventoryMovements.setWarehouseId(warehouseId);
                inventoryMovements.setMovementDate(movementDate);
                inventoryMovements.setQuantity(quantity);
                inventoryMovements.setMovementType(movementType);
                inventoryMovements.setEmployeeId(employeeId);
    
                // Ejecutar caso de uso
                createInventoryMovementsUC.execute(inventoryMovements);
    
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "InventoryMovements added successfully.");
    
                // Limpiar los campos de texto
                productIdField.setText("");
                warehouseIdField.setText("");
                datePicker.getModel().setValue(null);
                quantityField.setText("");
                movementTypeField.setText("");
                employeeIdField.setText("");
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
    
        JLabel idLabel = new JLabel("Enter InventoryMovements ID:");
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
                findInventoryMovementsUC.execute(id).ifPresentOrElse(
                        inventoryMovements -> showInventoryMovementsDetails(inventoryMovements),
                        () -> JOptionPane.showMessageDialog(this, "InventoryMovements not found.", "Error",
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
        // JPanel headerPanel = createHeaderPanel("InventoryMovements");

        // Crear tabla para mostrar los datos
        String[] columnQuantitys = { "ID", "ProductId", "WarehouseId", "MovementDate", "Quantity", "MovementType", "EmployeeId" };
        DefaultTableModel tableModel = new DefaultTableModel(columnQuantitys, 0); // Modelo de la tabla
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
            findAllInventoryMovementsUC.execute().forEach(inventoryMovements -> {
                Object[] row = { inventoryMovements.getId(), inventoryMovements.getProductId(),
                        inventoryMovements.getWarehouseId(), inventoryMovements.getMovementDate(), inventoryMovements.getQuantity(), inventoryMovements.getMovementType(), inventoryMovements.getEmployeeId() };
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
        JLabel productIdLabel = new JLabel("Enter new productId:");
        JTextField productIdField = new JTextField(10);
        JLabel warehouseIdLabel = new JLabel("Enter new warehouseId:");
        JTextField warehouseIdField = new JTextField(10);
        JLabel movementDateLabel = new JLabel("Select new movementDate:");
        
        // Configuración de JDatePicker
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    
        JLabel quantityLabel = new JLabel("Enter new quantity:");
        JTextField quantityField = new JTextField(10);
        JLabel movementTypeLabel = new JLabel("Enter new movementType:");
        JTextField movementTypeField = new JTextField(10);
        JLabel employeeIdLabel = new JLabel("Enter new employeeId:");
        JTextField employeeIdField = new JTextField(10);
    
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(warehouseIdLabel);
        formPanel.add(warehouseIdField);
        formPanel.add(movementDateLabel);
        formPanel.add(datePicker);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(movementTypeLabel);
        formPanel.add(movementTypeField);
        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
        
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
        
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int productId = Integer.parseInt(productIdField.getText().trim());
                int warehouseId = Integer.parseInt(warehouseIdField.getText().trim());
                java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                String movementDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                int quantity = Integer.parseInt(quantityField.getText().trim());
                String movementType = movementTypeField.getText().trim();
                int employeeId = Integer.parseInt(employeeIdField.getText().trim());
    
                if (selectedDate == null) {
                    throw new IllegalArgumentException("Movement date cannot be empty.");
                }
    
                // Actualizar InventoryMovements
                InventoryMovements inventoryMovements = new InventoryMovements();
                inventoryMovements.setId(id);
                inventoryMovements.setProductId(productId);
                inventoryMovements.setWarehouseId(warehouseId);
                inventoryMovements.setMovementDate(movementDate);
                inventoryMovements.setQuantity(quantity);
                inventoryMovements.setMovementType(movementType);
                inventoryMovements.setEmployeeId(employeeId);
    
                // Ejecutar caso de uso
                updateInventoryMovementsUC.execute(inventoryMovements);
    
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "InventoryMovements updated successfully.");
    
                // Limpiar los campos de texto
                idField.setText("");
                productIdField.setText("");
                warehouseIdField.setText("");
                datePicker.getModel().setValue(null);
                quantityField.setText("");
                movementTypeField.setText("");
                employeeIdField.setText("");
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
                deleteInventoryMovementsUC.execute(id);
    
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "InventoryMovements deleted successfully.");
    
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
    

    private void showInventoryMovementsDetails(InventoryMovements InventoryMovements) {
        String details = String.format("""
                InventoryMovements found:
                ID: %d
                ProductId: %d
                WarehouseId: %d
                MovementDate: %s
                Quantity: %d
                MovementType: %s
                EmployeeId: %d

                """, InventoryMovements.getId(), InventoryMovements.getProductId(), InventoryMovements.getWarehouseId(), InventoryMovements.getMovementDate(),
                InventoryMovements.getQuantity(), InventoryMovements.getMovementType(), InventoryMovements.getEmployeeId());
        JOptionPane.showMessageDialog(this, details, "InventoryMovements Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new InventoryMovementsAdapter();
    }

}