package com.esms.product.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.product.domain.entity.Product;
import com.esms.product.infrastructure.adapter.ProductAdapter;
import com.esms.product.application.CreateProductUC;
import com.esms.product.application.DeleteProductUC;
import com.esms.product.application.FindAllProductUC;
import com.esms.product.application.FindProductUC;
import com.esms.product.application.UpdateProductUC;
import com.esms.product.domain.service.ProductService;
import com.esms.product.infrastructure.repository.ProductRepository;
import com.esms.ui.CrudUi;

public class ProductAdapter extends JFrame {
    private final ProductService productService;
    private final CreateProductUC createProductUC;
    private final UpdateProductUC updateProductUC;
    private final FindAllProductUC findAllProductUC;
    private final FindProductUC findProductUC;
    private final DeleteProductUC deleteProductUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public ProductAdapter() {
        this.productService = new ProductRepository();
        this.createProductUC = new CreateProductUC(productService);
        this.updateProductUC = new UpdateProductUC(productService);
        this.findAllProductUC = new FindAllProductUC(productService);
        this.findProductUC = new FindProductUC(productService);
        this.deleteProductUC = new DeleteProductUC(productService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
                                                                                     // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Product Menu");
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
        JPanel addPanel = createOperationPanel("Add Product", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Product", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Product", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Product", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Product", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Product CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Product");
        JButton searchButton = createRoundedButton("Search Product");
        JButton findAllButton = createRoundedButton("Find All Product");
        JButton updateButton = createRoundedButton("Update Product");
        JButton deleteButton = createRoundedButton("Delete Product");
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

        JLabel nameLabel = new JLabel("Enter Product name:");
        JTextField nameField = new JTextField(10);
        JLabel descriptionLabel = new JLabel("Enter Product description:");
        JTextField descriptionField = new JTextField(10);
        JLabel priceLabel = new JLabel("Enter Product price:");
        JTextField priceField = new JTextField(10);
        JLabel categoryIdLabel = new JLabel("Enter Product categoryId:");
        JTextField categoryIdField = new JTextField(10);
        JLabel discountIdLabel = new JLabel("Enter Product discountId:");
        JTextField discountIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(categoryIdLabel);
        formPanel.add(categoryIdField);
        formPanel.add(discountIdLabel);
        formPanel.add(discountIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            int price = Integer.parseInt(priceField.getText().trim());
            int categoryId = Integer.parseInt(categoryIdField.getText().trim());
            int discountId = Integer.parseInt(discountIdField.getText().trim());

            // Validar si los campos están vacíos
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Product name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Product name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo Product
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategoryId(categoryId);
            product.setDiscountId(discountId);

            // Ejecutar caso de uso
            createProductUC.execute(product);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Product added successfully.");

            // Limpiar los campos de texto
            nameField.setText("");
            descriptionField.setText("");
            priceField.setText("");
            categoryIdField.setText("");
            discountIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Product ID:");

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
                findProductUC.execute(id).ifPresentOrElse(
                        product -> showProductDetails(product),
                        () -> JOptionPane.showMessageDialog(this, "Product not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Product");
    
        // Crear tabla para mostrar los datos
        String[] columnNames = { "ID", "Name", "Description", "Price", "CategoryId", "DiscountId" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // Modelo de la tabla
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
    
        // Añadir la tabla al panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JButton backButton = createRoundedButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        JButton searchButton = createRoundedButton("Search");
        searchButton.addActionListener(e -> {
            // Limpiar el modelo de la tabla antes de agregar nuevos datos
            tableModel.setRowCount(0);
    
            // Obtiene todos los productos y los añade a la tabla
            findAllProductUC.execute().forEach(product -> {
                Object[] row = { product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                        product.getCategoryId(), product.getDiscountId() };
                tableModel.addRow(row);
            });
        });
    
        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);
    
        // Añadir el panel del botón debajo de la tabla
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
    

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // 7 filas, 2 columnas, y espaciado de 10
        JLabel idLabel = new JLabel("Enter Product ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter new name:");
        JTextField nameField = new JTextField(20);
        JLabel descriptionLabel = new JLabel("Enter new description:");
        JTextField descriptionField = new JTextField(20);
        JLabel priceLabel = new JLabel("Enter new price:");
        JTextField priceField = new JTextField(20);
        JLabel categoryIdLabel = new JLabel("Enter new categoryId:");
        JTextField categoryIdField = new JTextField(20);
        JLabel discountIdLabel = new JLabel("Enter new discountId:");
        JTextField discountIdField = new JTextField(20);
        
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        // Agregar los componentes al panel de formulario
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(categoryIdLabel);
        formPanel.add(categoryIdField);
        formPanel.add(discountIdLabel);
        formPanel.add(discountIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        // Acción del botón "Update"
        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String description = descriptionField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int categoryId = Integer.parseInt(categoryIdField.getText().trim());
                int discountId = Integer.parseInt(discountIdField.getText().trim());
    
                // Validaciones de campos vacíos
                if (name.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Buscar producto y actualizar si es encontrado
                findProductUC.execute(id).ifPresentOrElse(
                    product -> {
                        product.setName(name);
                        product.setDescription(description);
                        product.setPrice(price);
                        product.setCategoryId(categoryId);
                        product.setDiscountId(discountId);
    
                        updateProductUC.execute(product);
                        JOptionPane.showMessageDialog(this, "Product updated successfully.");
    
                        // Limpiar los campos
                        idField.setText("");
                        nameField.setText("");
                        descriptionField.setText("");
                        priceField.setText("");
                        categoryIdField.setText("");
                        discountIdField.setText("");
                    },
                    () -> JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Acción del botón "Back"
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Product ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Product?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteProductUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Product deleted successfully.");
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

    private void showProductDetails(Product Product) {
        String details = String.format("""
                Product found:
                ID: %d
                Name: %s
                Description: %s
                Price: %f
                CategoryId: %d
                DiscountId: %d
                """, Product.getId(), Product.getName(), Product.getDescription(), Product.getPrice(),
                Product.getCategoryId(), Product.getDiscountId());
        JOptionPane.showMessageDialog(this, details, "Product Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ProductAdapter();
    }

}
