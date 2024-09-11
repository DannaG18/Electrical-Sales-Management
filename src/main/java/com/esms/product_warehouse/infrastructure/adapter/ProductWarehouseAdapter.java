package com.esms.product_warehouse.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.product_warehouse.application.CreateProductWarehouseUC;
import com.esms.product_warehouse.application.DeleteProductWarehouseUC;
import com.esms.product_warehouse.application.FindAllProductWarehouseUC;
import com.esms.product_warehouse.application.FindProductWarehouseUC;
import com.esms.product_warehouse.application.UpdateProductWarehouseUC;
import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;
import com.esms.product_warehouse.infrastructure.repository.ProductWarehouseRepository;
import com.esms.ui.CrudUi;

public class ProductWarehouseAdapter extends JFrame {
    private final ProductWarehouseService productWarehouseService;
    private final CreateProductWarehouseUC createProductWarehouseUC;
    private final UpdateProductWarehouseUC updateProductWarehouseUC;
    private final DeleteProductWarehouseUC deleteProductWarehouseUC;
    private final FindProductWarehouseUC findProductWarehouseUC;
    private final FindAllProductWarehouseUC findAllProductWarehouseUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public ProductWarehouseAdapter() {
        this.productWarehouseService = new ProductWarehouseRepository();
        this.createProductWarehouseUC = new CreateProductWarehouseUC(productWarehouseService);
        this.updateProductWarehouseUC = new UpdateProductWarehouseUC(productWarehouseService);
        this.deleteProductWarehouseUC = new DeleteProductWarehouseUC(productWarehouseService);
        this.findProductWarehouseUC = new FindProductWarehouseUC(productWarehouseService);
        this.findAllProductWarehouseUC = new FindAllProductWarehouseUC(productWarehouseService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
        // imagen
        setIconImage(windowIcon.getImage());
        setTitle("ProductWarehouse Menu");
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
        JPanel addPanel = createOperationPanel("Add ProductWarehouse", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search ProductWarehouse", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All ProductWarehouse", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update ProductWarehouse", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete ProductWarehouse", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("ProductWarehouse CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add ProductWarehouse");
        JButton searchButton = createRoundedButton("Search ProductWarehouse");
        JButton findAllButton = createRoundedButton("Find All ProductWarehouse");
        JButton updateButton = createRoundedButton("Update ProductWarehouse");
        JButton deleteButton = createRoundedButton("Delete ProductWarehouse");
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

    private JPanel createOperationPanel(String title, String cardStock, JPanel operationPanel) {
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

        JLabel productIdLabel = new JLabel("Enter productId:");
        JTextField productIdField = new JTextField(10);
        JLabel warehouseIdLabel = new JLabel("Enter warehouseId:");
        JTextField warehouseIdField = new JTextField(10);
        JLabel stockLabel = new JLabel("Enter stock:");
        JTextField stockField = new JTextField(10);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(stockLabel);
        formPanel.add(stockField);
        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(warehouseIdLabel);
        formPanel.add(warehouseIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            int stock = Integer.parseInt(stockField.getText().trim());
            int productId = Integer.parseInt(productIdField.getText().trim());
            int warehouseId = Integer.parseInt(warehouseIdField.getText().trim());

            // Crear nuevo ProductWarehouse
            ProductWarehouse productWarehouse = new ProductWarehouse();
            productWarehouse.setStock(stock);
            productWarehouse.setProductId(productId);
            productWarehouse.setWarehouseId(warehouseId);

            // Ejecutar caso de uso
            createProductWarehouseUC.execute(productWarehouse);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "ProductWarehouse added successfully.");

            // Limpiar los campos de texto
            stockField.setText("");
            productIdField.setText("");
            warehouseIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter ProductWarehouse ID:");

        JTextField idField = new JTextField(5);
        idField.setPreferredSize(new Dimension(300, 250)); // Ajusta la altura del campo de texto

        JButton submitButton = createRoundedButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón

        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                findProductWarehouseUC.execute(id).ifPresentOrElse(
                        productWarehouse -> showProductWarehouseDetails(productWarehouse),
                        () -> JOptionPane.showMessageDialog(this, "ProductWarehouse not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("ProductWarehouse");

        // Crear tabla para mostrar los datos
        String[] columnStocks = { "ID", "ProductId", "WarehouseId", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(columnStocks, 0); // Modelo de la tabla
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
            findAllProductWarehouseUC.execute().forEach(productWarehouse -> {
                Object[] row = { productWarehouse.getId(), productWarehouse.getProductId(), productWarehouse.getWarehouseId(),  productWarehouse.getStock()};
                tableModel.addRow(row);
            });
        });


        JButton backButton = createRoundedButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        tableModel.setRowCount(0);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter ID:");
        JTextField idField = new JTextField(20);
        JLabel productIdLabel = new JLabel("Enter new productId:");
        JTextField productIdField = new JTextField(20);
        JLabel warehouseIdLabel = new JLabel("Enter new warehouseId:");
        JTextField warehouseIdField = new JTextField(20);
        JLabel stockLabel = new JLabel("Enter new stock:");
        JTextField stockField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(productIdLabel);
        formPanel.add(productIdField);
        formPanel.add(warehouseIdLabel);
        formPanel.add(warehouseIdField);
        formPanel.add(stockLabel);
        formPanel.add(stockField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int stock = Integer.parseInt(stockField.getText().trim());
                int productId = Integer.parseInt(productIdField.getText().trim());
                int warehouseId = Integer.parseInt(warehouseIdField.getText().trim());

                findProductWarehouseUC.execute(id).ifPresentOrElse(
                        ProductWarehouse -> {
                            ProductWarehouse.setStock(stock);
                            ProductWarehouse.setProductId(productId);
                            ProductWarehouse.setWarehouseId(warehouseId);

                            updateProductWarehouseUC.execute(ProductWarehouse);
                            JOptionPane.showMessageDialog(this, "ProductWarehouse updated successfully.");
                            idField.setText("");

                            stockField.setText("");
                            productIdField.setText("");
                            warehouseIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "ProductWarehouse not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter ProductWarehouse ID:");
        JTextField idField = new JTextField(20);
        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int confirmation = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this ProductWarehouse?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteProductWarehouseUC.execute(id);
                    JOptionPane.showMessageDialog(this, "ProductWarehouse deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showProductWarehouseDetails(ProductWarehouse ProductWarehouse) {
        String details = String.format("""
                ProductWarehouse found:
                ID: %d
                ProductId: %d
                WarehouseId: %d
                Stock: %d
                """, ProductWarehouse.getId(), ProductWarehouse.getProductId(), ProductWarehouse.getWarehouseId(), ProductWarehouse.getStock());
        JOptionPane.showMessageDialog(this, details, "ProductWarehouse Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ProductWarehouseAdapter();
    }

}

