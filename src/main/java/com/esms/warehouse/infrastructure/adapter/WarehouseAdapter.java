package com.esms.warehouse.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.ui.CrudUi;
import com.esms.warehouse.application.CreateWarehouseUC;
import com.esms.warehouse.application.DeleteWarehouseUC;
import com.esms.warehouse.application.FindAllWarehouseUC;
import com.esms.warehouse.application.FindWarehouseUC;
import com.esms.warehouse.application.UpdateWarehouseUC;
import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;
import com.esms.warehouse.infrastructure.repository.WarehouseRepository;

public class WarehouseAdapter extends JFrame {
    private final WarehouseService warehouseService;
    private final CreateWarehouseUC createWarehouseUC;
    private final FindWarehouseUC findWarehouseUC;
    private final UpdateWarehouseUC updateWarehouseUC;
    private final FindAllWarehouseUC findAllWarehouseUC;
    private final DeleteWarehouseUC deleteWarehouseUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public WarehouseAdapter() {
        this.warehouseService = new WarehouseRepository();
        this.createWarehouseUC = new CreateWarehouseUC(warehouseService);
        this.findWarehouseUC = new FindWarehouseUC(warehouseService);
        this.updateWarehouseUC = new UpdateWarehouseUC(warehouseService);
        this.findAllWarehouseUC = new FindAllWarehouseUC(warehouseService);
        this.deleteWarehouseUC = new DeleteWarehouseUC(warehouseService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
        // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Warehouse Menu");
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
        JPanel addPanel = createOperationPanel("Add Warehouse", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Warehouse", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Warehouse", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Warehouse", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Warehouse", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Warehouse CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Warehouse");
        JButton searchButton = createRoundedButton("Search Warehouse");
        JButton findAllButton = createRoundedButton("Find All Warehouse");
        JButton updateButton = createRoundedButton("Update Warehouse");
        JButton deleteButton = createRoundedButton("Delete Warehouse");
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

        JLabel nameLabel = new JLabel("Enter Warehouse name:");
        JTextField nameField = new JTextField(10);
        JLabel branchIdLabel = new JLabel("Enter Warehouse branchId:");
        JTextField branchIdField = new JTextField(10);
        JLabel cityIdLabel = new JLabel("Enter Warehouse cityId:");
        JTextField cityIdField = new JTextField(10);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(branchIdLabel);
        formPanel.add(branchIdField);
        formPanel.add(cityIdLabel);
        formPanel.add(cityIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            int branchId = Integer.parseInt(branchIdField.getText().trim());
            String cityId = cityIdField.getText().trim();

            // Validar si los campos están vacíos
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Warehouse name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo Warehouse
            Warehouse warehouse = new Warehouse();
            warehouse.setName(name);
            warehouse.setBranchId(branchId);
            warehouse.setCityId(cityId);

            // Ejecutar caso de uso
            createWarehouseUC.execute(warehouse);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Warehouse added successfully.");

            // Limpiar los campos de texto
            nameField.setText("");
            branchIdField.setText("");
            cityIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Warehouse ID:");

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
                findWarehouseUC.execute(id).ifPresentOrElse(
                        warehouse -> showWarehouseDetails(warehouse),
                        () -> JOptionPane.showMessageDialog(this, "Warehouse not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Warehouse");

        // Crear tabla para mostrar los datos
        String[] columnNames = { "ID", "Name", "BranchId", "CityId" };
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
            findAllWarehouseUC.execute().forEach(warehouse -> {
                Object[] row = { warehouse.getId(), warehouse.getName(), warehouse.getBranchId(),
                        warehouse.getCityId() };
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

        JLabel idLabel = new JLabel("Enter Warehouse ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter new Warehouse name:");
        JTextField nameField = new JTextField(20);
        JLabel branchIdLabel = new JLabel("Enter new Warehouse branchId:");
        JTextField branchIdField = new JTextField(20);
        JLabel cityIdLabel = new JLabel("Enter new Warehouse cityId:");
        JTextField cityIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(branchIdLabel);
        formPanel.add(branchIdField);
        formPanel.add(cityIdLabel);
        formPanel.add(cityIdField);
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
                String name = nameField.getText().trim();
                int branchId = Integer.parseInt(branchIdField.getText().trim());
                String cityId = cityIdField.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Warehouse name cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                findWarehouseUC.execute(id).ifPresentOrElse(
                        Warehouse -> {
                            Warehouse.setName(name);
                            Warehouse.setBranchId(branchId);
                            Warehouse.setCityId(cityId);

                            updateWarehouseUC.execute(Warehouse);
                            JOptionPane.showMessageDialog(this, "Warehouse updated successfully.");
                            idField.setText("");

                            nameField.setText("");
                            branchIdField.setText("");
                            cityIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Warehouse not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter Warehouse ID:");
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
                        "Are you sure you want to delete this Warehouse?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteWarehouseUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Warehouse deleted successfully.");
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

    private void showWarehouseDetails(Warehouse Warehouse) {
        String details = String.format("""
                Warehouse found:
                ID: %d
                Name: %s
                BranchId: %d
                CityId: %s
                """, Warehouse.getId(), Warehouse.getName(), Warehouse.getBranchId(), Warehouse.getCityId());
        JOptionPane.showMessageDialog(this, details, "Warehouse Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new WarehouseAdapter();
    }

}
