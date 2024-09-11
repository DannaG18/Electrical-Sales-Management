package com.esms.employee.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.employee.application.CreateEmployeeUC;
import com.esms.employee.application.DeleteEmployeeUC;
import com.esms.employee.application.FindAllEmployeeUC;
import com.esms.employee.application.FindEmployeeUC;
import com.esms.employee.application.UpdateEmployeeUC;
import com.esms.employee.domain.service.EmployeeService;
import com.esms.employee.infrastructure.repository.EmployeeRepository;
import com.esms.ui.CrudUi;
import com.esms.employee.domain.entity.Employee;
import com.esms.employee.infrastructure.adapter.EmployeeAdapter;

public class EmployeeAdapter extends JFrame {
    private final EmployeeService employeeService;
    private final CreateEmployeeUC createEmployeeUC;
    private final UpdateEmployeeUC updateEmployeeUC;
    private final DeleteEmployeeUC deleteEmployeeUC;
    private final FindEmployeeUC findEmployeeUC;
    private final FindAllEmployeeUC findAllEmployeeUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public EmployeeAdapter() {
        this.employeeService = new EmployeeRepository();
        this.createEmployeeUC = new CreateEmployeeUC(employeeService);
        this.updateEmployeeUC = new UpdateEmployeeUC(employeeService);
        this.deleteEmployeeUC = new DeleteEmployeeUC(employeeService);
        this.findEmployeeUC = new FindEmployeeUC(employeeService);
        this.findAllEmployeeUC = new FindAllEmployeeUC(employeeService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
        // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Employee Menu");
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
        JPanel addPanel = createOperationPanel("Add Employee", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Employee", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Employee", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Employee", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Employee", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Employee CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Employee");
        JButton searchButton = createRoundedButton("Search Employee");
        JButton findAllButton = createRoundedButton("Find All Employee");
        JButton updateButton = createRoundedButton("Update Employee");
        JButton deleteButton = createRoundedButton("Delete Employee");
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

    private JPanel createOperationPanel(String title, String cardFirstName, JPanel operationPanel) {
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

        JPanel formPanel = new JPanel(new GridLayout(9, 1, 10, 10));

        JLabel firstNameLabel = new JLabel("Enter new firstName:");
        JTextField firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Enter new lastName:");
        JTextField lastNameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Enter new email:");
        JTextField emailField = new JTextField(20);
        JLabel phoneIdLabel = new JLabel("Enter new phoneId:");
        JTextField phoneIdField = new JTextField(20);
        JLabel hireDateLabel = new JLabel("Enter new hireDate:");
        JTextField hireDateField = new JTextField(20);
        JLabel salaryLabel = new JLabel("Enter new salary:");
        JTextField salaryField = new JTextField(20);
        JLabel roleIdLabel = new JLabel("Enter new roleId:");
        JTextField roleIdField = new JTextField(20);
        JLabel branchIdLabel = new JLabel("Enter new branchId:");
        JTextField branchIdField = new JTextField(20);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneIdLabel);
        formPanel.add(phoneIdField);
        formPanel.add(hireDateLabel);
        formPanel.add(hireDateField);
        formPanel.add(salaryLabel);
        formPanel.add(salaryField);
        formPanel.add(roleIdLabel);
        formPanel.add(roleIdField);
        formPanel.add(branchIdLabel);
        formPanel.add(branchIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            int phoneId = Integer.parseInt(phoneIdField.getText().trim());
            String hireDate = hireDateField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            int roleId = Integer.parseInt(roleIdField.getText().trim());
            int branchId = Integer.parseInt(branchIdField.getText().trim());

            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (hireDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo Employee
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setPhoneId(phoneId);
            employee.setHireDate(hireDate);
            employee.setSalary(salary);
            employee.setRoleId(roleId);
            employee.setBranchId(branchId);

            // Ejecutar caso de uso
            createEmployeeUC.execute(employee);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Employee added successfully.");

            // Limpiar los campos de texto
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneIdField.setText("");
            hireDateField.setText("");
            salaryField.setText("");
            roleIdField.setText("");
            branchIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Employee ID:");

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
                findEmployeeUC.execute(id).ifPresentOrElse(
                        employee -> showEmployeeDetails(employee),
                        () -> JOptionPane.showMessageDialog(this, "Employee not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Employee");

        // Crear tabla para mostrar los datos
        String[] columnNames = { "ID", "FirstName", "LastName", "Email", "PhoneId", "HireDate", "Salary", "RoleId", "BranchId" };
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
            findAllEmployeeUC.execute().forEach(employee -> {
                Object[] row = { employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneId(), employee.getHireDate(), employee.getSalary(),employee.getRoleId(),
                        employee.getBranchId() };
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

        JPanel formPanel = new JPanel(new GridLayout(10, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Employee ID:");
        JTextField idField = new JTextField(20);
        JLabel firstNameLabel = new JLabel("Enter new firstName:");
        JTextField firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Enter new lastName:");
        JTextField lastNameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Enter new email:");
        JTextField emailField = new JTextField(20);
        JLabel phoneIdLabel = new JLabel("Enter new phoneId:");
        JTextField phoneIdField = new JTextField(20);
        JLabel hireDateLabel = new JLabel("Enter new hireDate:");
        JTextField hireDateField = new JTextField(20);
        JLabel salaryLabel = new JLabel("Enter new salary:");
        JTextField salaryField = new JTextField(20);
        JLabel roleIdLabel = new JLabel("Enter new roleId:");
        JTextField roleIdField = new JTextField(20);
        JLabel branchIdLabel = new JLabel("Enter new branchId:");
        JTextField branchIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneIdLabel);
        formPanel.add(phoneIdField);
        formPanel.add(hireDateLabel);
        formPanel.add(hireDateField);
        formPanel.add(salaryLabel);
        formPanel.add(salaryField);
        formPanel.add(roleIdLabel);
        formPanel.add(roleIdField);
        formPanel.add(branchIdLabel);
        formPanel.add(branchIdField);
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
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String email = emailField.getText().trim();
                int phoneId = Integer.parseInt(phoneIdField.getText().trim());
                String hireDate = hireDateField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());
                int roleId = Integer.parseInt(roleIdField.getText().trim());
                int branchId = Integer.parseInt(branchIdField.getText().trim());

                if (firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (hireDate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Employee firstName cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                findEmployeeUC.execute(id).ifPresentOrElse(
                        Employee -> {
                            Employee.setFirstName(firstName);
                            Employee.setLastName(lastName);
                            Employee.setEmail(email);
                            Employee.setPhoneId(phoneId);
                            Employee.setHireDate(hireDate);
                            Employee.setSalary(salary);
                            Employee.setRoleId(roleId);
                            Employee.setBranchId(branchId);

                            updateEmployeeUC.execute(Employee);
                            JOptionPane.showMessageDialog(this, "Employee updated successfully.");
                            idField.setText("");

                            firstNameField.setText("");
                            lastNameField.setText("");
                            emailField.setText("");
                            phoneIdField.setText("");
                            hireDateField.setText("");
                            salaryField.setText("");
                            roleIdField.setText("");
                            branchIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Employee not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter Employee ID:");
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
                        "Are you sure you want to delete this Employee?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteEmployeeUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
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

    private void showEmployeeDetails(Employee Employee) {
        String details = String.format("""
                Employee found:
                ID: %d
                FirstName: %s
                LastName: %s
                Email: %s
                PhoneId: %d
                HireDate: %s
                Salary: %d
                RoleId: %d
                BranchId: %d
                """, Employee.getId(), Employee.getFirstName(), Employee.getLastName(), Employee.getEmail(), Employee.getPhoneId(), Employee.getSalary(), Employee.getHireDate(), Employee.getRoleId(), Employee.getBranchId());
        JOptionPane.showMessageDialog(this, details, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new EmployeeAdapter();
    }

}
