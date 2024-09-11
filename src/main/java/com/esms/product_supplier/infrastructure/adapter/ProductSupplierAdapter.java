package com.esms.product_supplier.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.product_supplier.application.CreateProductSupplierUC;
import com.esms.product_supplier.application.DeleteProductSupplierUC;
import com.esms.product_supplier.application.FindAllProductSupplierUC;
import com.esms.product_supplier.application.FindProductSupplierUC;
import com.esms.product_supplier.application.UpdateProductSupplierUC;
import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;
import com.esms.product_supplier.infrastructure.repository.ProductSupplierRepository;
import com.esms.ui.CrudUi;

public class ProductSupplierAdapter extends JFrame{
    private final ProductSupplierService productSupplierService;
    private final CreateProductSupplierUC createProductSupplierUC;
    private final UpdateProductSupplierUC updateProductSupplierUC;
    private final FindProductSupplierUC findProductSupplierUC;
    private final FindAllProductSupplierUC findAllProductSupplierUC;
    private final DeleteProductSupplierUC deleteProductSupplierUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public ProductSupplierAdapter(){
        this.productSupplierService = new ProductSupplierRepository();
        this.createProductSupplierUC = new CreateProductSupplierUC(productSupplierService);
        this.updateProductSupplierUC = new UpdateProductSupplierUC(productSupplierService);
        this.findProductSupplierUC = new FindProductSupplierUC(productSupplierService);
        this.findAllProductSupplierUC = new FindAllProductSupplierUC(productSupplierService);
        this.deleteProductSupplierUC = new DeleteProductSupplierUC(productSupplierService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
                                                                                     // imagen
        setIconImage(windowIcon.getImage());
        setTitle("ProductSupplier Menu");
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
        JPanel addPanel = createOperationPanel("Add ProductSupplier", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search ProductSupplier", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All ProductSupplier", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update ProductSupplier", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete ProductSupplier", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("ProductSupplier CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add ProductSupplier");
        JButton searchButton = createRoundedButton("Search ProductSupplier");
        JButton findAllButton = createRoundedButton("Find All ProductSupplier");
        JButton updateButton = createRoundedButton("Update ProductSupplier");
        JButton deleteButton = createRoundedButton("Delete ProductSupplier");
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

    private JPanel createOperationPanel(String title, String cardName, JPanel operationPanel) {
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

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel productIdLabel = new JLabel("Enter product_id:");
        JTextField productIdField = new JTextField(10);
        JLabel supplierIdLabel = new JLabel("Enter supplier_id:");
        JTextField supplierIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(supplierIdLabel);
        formPanel.add(supplierIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            int productId = Integer.parseInt(productIdField.getText().trim());
            int supplierId = Integer.parseInt(supplierIdField.getText().trim());


            // Crear nuevo ProductSupplier
            ProductSupplier productSupplier = new ProductSupplier();
            productSupplier.setProductId(productId);
            productSupplier.setSupplierId(supplierId);

            // Ejecutar caso de uso
            createProductSupplierUC.execute(productSupplier);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "ProductSupplier added successfully.");

            // Limpiar los campos de texto
            productIdField.setText("");
            supplierIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel productIdLabel = new JLabel("Enter Product ID:");
        JTextField productIdField = new JTextField(10);
        JLabel supplierIdLabel = new JLabel("Enter Supplier ID:");
        JTextField supplierIdField = new JTextField(10);

        JButton submitButton = createRoundedButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón

        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón

        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(supplierIdLabel);
        formPanel.add(supplierIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int productId = Integer.parseInt(productIdField.getText().trim());
                int supplierId = Integer.parseInt(supplierIdField.getText().trim());
                
                findProductSupplierUC.execute(productId, supplierId).ifPresentOrElse(
                        productSupplier -> showProductSupplierDetails(productSupplier),
                        () -> JOptionPane.showMessageDialog(this, "ProductSupplier not found.", "Error", JOptionPane.ERROR_MESSAGE));
                
                        productIdField.setText(""); // Limpiar el campo de texto
                        supplierIdField.setText("");
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
        JPanel headerPanel = createHeaderPanel("ProductSupplier");

        // Crear tabla para mostrar los datos
        String[] columnNames = {"ProductId","ProductName", "SupplierId", "SupplierName" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // Modelo de la tabla
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Añadir la tabla al panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton searchButton = createRoundedButton("Search");
        searchButton.addActionListener(e -> {
            // Limpiar el modelo de la tabla antes de agregar nuevos datos
            tableModel.setRowCount(0);
    
            // Obtiene todos los productos y los añade a la tabla
            findAllProductSupplierUC.execute().forEach(productSupplier -> {
                Object[] row = { productSupplier.getProductId(), productSupplier.getProductName(), productSupplier.getSupplierId(), productSupplier.getSupplierName() };
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

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel productIdLabel = new JLabel("Enter product_id:");
        JTextField productIdField = new JTextField(10);
        JLabel supplierIdLabel = new JLabel("Enter supplier_id:");
        JTextField supplierIdField = new JTextField(10);
        JLabel newProductIdLabel = new JLabel("Enter new product_id:");
        JTextField newProductIdField = new JTextField(10);
        JLabel newSupplierIdLabel = new JLabel("Enter new supplier_id:");
        JTextField newSupplierIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(supplierIdLabel); 
        formPanel.add(supplierIdField);

        formPanel.add(newProductIdLabel);
        formPanel.add(newProductIdField);
        formPanel.add(newSupplierIdLabel); 
        formPanel.add(newSupplierIdField);

        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int productId = Integer.parseInt(productIdField.getText().trim());
                int supplierId = Integer.parseInt(supplierIdField.getText().trim());

                int newProductId = Integer.parseInt(newProductIdField.getText().trim());
                int newSupplierId = Integer.parseInt(newSupplierIdField.getText().trim());



                findProductSupplierUC.execute(productId, supplierId).ifPresentOrElse(
                        ProductSupplier -> {

                            ProductSupplier.setProductId(newProductId);
                            ProductSupplier.setSupplierId(newSupplierId);

                            updateProductSupplierUC.execute(ProductSupplier);
                            
                            JOptionPane.showMessageDialog(this, "ProductSupplier updated successfully.");
                            productIdField.setText("");
                            supplierIdField.setText("");

                            newProductIdField.setText("");
                            newSupplierIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "ProductSupplier not found.", "Error",
                                JOptionPane.ERROR_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel productIdLabel = new JLabel("Enter Product ID:");
        JLabel supplierIdLabel = new JLabel("Enter Supplier ID:");

        JTextField productIdField = new JTextField(10);
        JTextField supplierIdField = new JTextField(10);

        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(supplierIdLabel);
        formPanel.add(supplierIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int productId = Integer.parseInt(productIdField.getText().trim());
                int supplierId = Integer.parseInt(supplierIdField.getText().trim());
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this ProductSupplier?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteProductSupplierUC.execute(productId,  supplierId);

                    JOptionPane.showMessageDialog(this, "ProductSupplier deleted successfully.");

                    productIdField.setText("");
                    supplierIdField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showProductSupplierDetails(ProductSupplier ProductSupplier) {
        String details = String.format("""
                ProductSupplier found:
                ProductId: %d
                ProductName: %s
                SuppliertId: %d
                SupplierName: %s
                """, ProductSupplier.getProductId(), ProductSupplier.getProductName(), ProductSupplier.getSupplierId(), ProductSupplier.getSupplierName());
        JOptionPane.showMessageDialog(this, details, "ProductSupplier Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ProductSupplierAdapter();
    }

}

