package com.esms.employee_roles.infrastructure.adapters;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.employee_roles.application.CreateEmployeeRoleUC;
import com.esms.employee_roles.application.DeleteEmployeeRoleUC;
import com.esms.employee_roles.application.FindAllEmployeeRoleUC;
import com.esms.employee_roles.application.FindEmployeeRoleUC;
import com.esms.employee_roles.application.UpdateEmployeeRoleUC;
import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;
import com.esms.employee_roles.infrastructure.repository.EmployeeRoleRepo;
import com.esms.ui.CrudUi;

public class EmployeeRoleAdapter extends JFrame{
    private final EmployeeRoleService employeeRoleService;
    private final CreateEmployeeRoleUC createEmployeeRoleUC;
    private final UpdateEmployeeRoleUC updateEmployeeRoleUC;
    private final FindEmployeeRoleUC findEmployeeRoleUC;
    private final FindAllEmployeeRoleUC findAllEmployeeRoleUC;
    private final DeleteEmployeeRoleUC deleteEmployeeRoleUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi; 

    public EmployeeRoleAdapter() {
        this.employeeRoleService = new EmployeeRoleRepo();
        this.createEmployeeRoleUC = new CreateEmployeeRoleUC(employeeRoleService);
        this.updateEmployeeRoleUC = new UpdateEmployeeRoleUC(employeeRoleService);
        this.findEmployeeRoleUC = new FindEmployeeRoleUC(employeeRoleService);
        this.findAllEmployeeRoleUC = new FindAllEmployeeRoleUC(employeeRoleService);
        this.deleteEmployeeRoleUC = new DeleteEmployeeRoleUC(employeeRoleService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu imagen
        setIconImage(windowIcon.getImage());
        setTitle("Employee Role Menu");
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
        JPanel addPanel = createOperationPanel("Add EmployeeRole", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search EmployeeRole", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All EmployeeRole", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update EmployeeRole", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete EmployeeRole", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("EmployeeRole CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add EmployeeRole");
        JButton searchButton = createRoundedButton("Search EmployeeRole");
        JButton findAllButton = createRoundedButton("Find All Employee Role");
        JButton updateButton = createRoundedButton("Update EmployeeRole");
        JButton deleteButton = createRoundedButton("Delete EmployeeRole");
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
    
        JLabel nameLabel = new JLabel("Enter role:");
        JTextField nameField = new JTextField(10);
        JLabel descriptionLabel = new JLabel("Enter description:");
        JTextField descriptionField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        // Acción del botón de envío con validaciones
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
    
            // Validar si los campos están vacíos
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "EmployeeRole name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "EmployeeRole description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Crear nuevo EmployeeRole
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setRole_name(name);
            employeeRole.setDescription(description);
    
            // Ejecutar caso de uso
            createEmployeeRoleUC.execute(employeeRole);
    
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "EmployeeRole added successfully.");
    
            // Limpiar los campos de texto
            nameField.setText("");
            descriptionField.setText("");
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
    
        JLabel idLabel = new JLabel("Enter EmployeeRole ID:");
        JTextField idField = new JTextField(5);
    
        JButton submitButton = createRoundedButton("Search");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        // Acción del botón de búsqueda con validación de ID numérico
        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                findEmployeeRoleUC.execute(id).ifPresentOrElse(
                    employeeRole -> showEmployeeRoleDetails(employeeRole),
                    () -> JOptionPane.showMessageDialog(this, "EmployeeRole not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                idField.setText(""); // Limpiar el campo de texto
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createFindAllPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        // Crear encabezado del panel
        JPanel headerPanel = createHeaderPanel("Employee Roles");
    
        // Crear tabla para mostrar los datos
        String[] columnNames = {"ID", "Role Name", "Description"};
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
            findAllEmployeeRoleUC.execute().forEach(employeeRole -> {
                Object[] row = {employeeRole.getId(), employeeRole.getRole_name(), employeeRole.getDescription()};
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

        JLabel idLabel = new JLabel("Enter EmployeeRole ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter new role:");
        JTextField nameField = new JTextField(20);
        JLabel descriptionLabel = new JLabel("Enter new description:");
        JTextField descriptionField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
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
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "EmployeeRole name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                findEmployeeRoleUC.execute(id).ifPresentOrElse(
                        EmployeeRole -> {
                            EmployeeRole.setRole_name(name);
                            EmployeeRole.setDescription(descriptionField.getText().trim());
                            updateEmployeeRoleUC.execute(EmployeeRole);
                            JOptionPane.showMessageDialog(this, "EmployeeRole updated successfully.");
                            idField.setText("");
                            nameField.setText("");
                            descriptionField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "EmployeeRole not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter EmployeeRole ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this EmployeeRole?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteEmployeeRoleUC.execute(id);
                    JOptionPane.showMessageDialog(this, "EmployeeRole deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showEmployeeRoleDetails(EmployeeRole EmployeeRole) {
        String details = String.format("""
                EmployeeRole found:
                ID: %d
                Name: %s
                Description: %s
                """, EmployeeRole.getId(), EmployeeRole.getRole_name(), EmployeeRole.getDescription());
        JOptionPane.showMessageDialog(this, details, "EmployeeRole Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new EmployeeRoleAdapter();
    }

}