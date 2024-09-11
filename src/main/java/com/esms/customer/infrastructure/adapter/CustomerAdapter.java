package com.esms.customer.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.customer.application.CreateCustomerUC;
import com.esms.customer.application.DeleteCustomerUC;
import com.esms.customer.application.FindAllCustomerUC;
import com.esms.customer.application.FindCustomerUC;
import com.esms.customer.application.UpdateCustomerUC;
import com.esms.customer.domain.service.CustomerService;
import com.esms.customer.infrastructure.repository.CustomerRepository;
import com.esms.ui.CrudUi;
import com.esms.customer.domain.entity.Customer;
import com.esms.customer.infrastructure.adapter.CustomerAdapter;

public class CustomerAdapter extends JFrame {
    private final CustomerService customerService;
    private final CreateCustomerUC createCustomerUC;
    private final UpdateCustomerUC updateCustomerUC;
    private final FindAllCustomerUC findAllCustomerUC;
    private final FindCustomerUC findCustomerUC;
    private final DeleteCustomerUC deleteCustomerUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public CustomerAdapter() {
        this.customerService = new CustomerRepository();
        this.createCustomerUC = new CreateCustomerUC(customerService);
        this.updateCustomerUC = new UpdateCustomerUC(customerService);
        this.findAllCustomerUC = new FindAllCustomerUC(customerService);
        this.findCustomerUC = new FindCustomerUC(customerService);
        this.deleteCustomerUC = new DeleteCustomerUC(customerService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
                                                                                     // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Customer Menu");
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
        JPanel addPanel = createOperationPanel("Add Customer", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Customer", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Customer", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Customer", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Customer", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Customer CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Customer");
        JButton searchButton = createRoundedButton("Search Customer");
        JButton findAllButton = createRoundedButton("Find All Customer");
        JButton updateButton = createRoundedButton("Update Customer");
        JButton deleteButton = createRoundedButton("Delete Customer");
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

        JLabel nameLabel = new JLabel("Enter Customer name:");
        JTextField nameField = new JTextField(10);
        JLabel emailLabel = new JLabel("Enter Customer email:");
        JTextField emailField = new JTextField(10);
        JLabel phoneIdLabel = new JLabel("Enter Customer phoneId:");
        JLabel cityIdLabel = new JLabel("Enter Customer cityId:");
        JTextField cityIdField = new JTextField(10);
        JTextField phoneIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneIdLabel);
        formPanel.add(phoneIdField);
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
            String name = cityIdField.getText().trim();
            String email = emailField.getText().trim();
            int phoneId = Integer.parseInt(phoneIdField.getText().trim());
            String cityId = cityIdField.getText().trim();

            // Validar si los campos están vacíos
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo Customer
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneId(phoneId);
            customer.setCityId(cityId);

            // Ejecutar caso de uso
            createCustomerUC.execute(customer);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Customer added successfully.");

            // Limpiar los campos de texto
            nameField.setText("");
            emailField.setText("");
            phoneIdField.setText("");
            cityIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Customer ID:");

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
                findCustomerUC.execute(id).ifPresentOrElse(
                        customer -> showCustomerDetails(customer),
                        () -> JOptionPane.showMessageDialog(this, "Customer not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Customer");

        // Crear tabla para mostrar los datos
        String[] columnNames = { "ID", "Name", "Email", "PhoneId", "CityId" };
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
            findAllCustomerUC.execute().forEach(customer -> {
                Object[] row = { customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneId(),
                        customer.getCityId() };
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

        JLabel idLabel = new JLabel("Enter Customer ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter new Customer name:");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Enter new Customer email:");
        JTextField emailField = new JTextField(20);
        JLabel phoneIdLabel = new JLabel("Enter new Customer phoneId:");
        JTextField phoneIdField = new JTextField(20);
        JLabel cityIdLabel = new JLabel("Enter new Customer cityId:");
        JTextField cityIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailField);
        formPanel.add(emailLabel);
        formPanel.add(phoneIdField);
        formPanel.add(phoneIdLabel);
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
                String name = cityIdField.getText().trim();
                String email = emailField.getText().trim();
                int phoneId = Integer.parseInt(phoneIdField.getText().trim());
                String cityId = cityIdField.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Customer cityId cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Customer cityId cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                findCustomerUC.execute(id).ifPresentOrElse(
                        Customer -> {
                            Customer.setName(name);
                            Customer.setEmail(email);
                            Customer.setPhoneId(phoneId);
                            Customer.setCityId(cityId);
                            updateCustomerUC.execute(Customer);
                            JOptionPane.showMessageDialog(this, "Customer updated successfully.");
                            idField.setText("");

                            nameField.setText("");
                            emailField.setText("");
                            phoneIdField.setText("");
                            cityIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Customer not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter Customer ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Customer?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteCustomerUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
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

    private void showCustomerDetails(Customer Customer) {
        String details = String.format("""
                Customer found:
                ID: %d
                Name: %s
                Email: %s
                Phone: %d
                CityId: %s
                """, Customer.getId(), Customer.getName(), Customer.getEmail(), Customer.getPhoneId(),
                Customer.getCityId());
        JOptionPane.showMessageDialog(this, details, "Customer Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new CustomerAdapter();
    }

}
