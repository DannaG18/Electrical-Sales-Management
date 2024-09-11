package com.esms.address.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.address.application.CreateAddressUC;
import com.esms.address.application.DeleteAddressUC;
import com.esms.address.application.FindAddressUC;
import com.esms.address.application.FindAllAddressUC;
import com.esms.address.application.UpdateAddressUC;
import com.esms.address.domain.service.AddressService;
import com.esms.address.infrastructure.repository.AddressRepository;
import com.esms.address.domain.entity.Address;
import com.esms.address.infrastructure.adapter.AddressAdapter;

public class AddressAdapter extends JFrame {
    private final AddressService addressService;
    private final CreateAddressUC createAddressUC;
    private final UpdateAddressUC updateAddressUC;
    private final FindAllAddressUC findAllAddressUC;
    private final FindAddressUC findAddressUC;
    private final DeleteAddressUC deleteAddressUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public AddressAdapter() {
        this.addressService = new AddressRepository();
        this.createAddressUC = new CreateAddressUC(addressService);
        this.updateAddressUC = new UpdateAddressUC(addressService);
        this.findAllAddressUC = new FindAllAddressUC(addressService);
        this.findAddressUC = new FindAddressUC(addressService);
        this.deleteAddressUC = new DeleteAddressUC(addressService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
                                                                                     // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Address Menu");
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
        JPanel addPanel = createOperationPanel("Add Address", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Address", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Address", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Address", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Address", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Address CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Address");
        JButton searchButton = createRoundedButton("Search Address");
        JButton findAllButton = createRoundedButton("Find All Address");
        JButton updateButton = createRoundedButton("Update Address");
        JButton deleteButton = createRoundedButton("Delete Address");
        JButton exitButton = createRoundedButton("Exit");

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
        exitButton.addActionListener(e -> System.exit(0));

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

        JLabel streetLabel = new JLabel("Enter Address street:");
        JTextField streetField = new JTextField(10);
        JLabel postalCodeLabel = new JLabel("Enter Address postalCode:");
        JTextField postalCodeField = new JTextField(10);
        JLabel cityIdLabel = new JLabel("Enter Address cityId:");
        JTextField cityIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(postalCodeLabel);
        formPanel.add(postalCodeField);
        formPanel.add(streetLabel);
        formPanel.add(streetField);
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
            String street = streetField.getText().trim();
            String postalCode = streetField.getText().trim();
            String cityId = streetField.getText().trim();

            // Validar si los campos están vacíos
            if (street.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address street cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (postalCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address street cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cityId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address street cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo Address
            Address address = new Address();
            address.setStreet(street);
            address.setPostalCode(postalCode);
            address.setCityId(cityId);

            // Ejecutar caso de uso
            createAddressUC.execute(address);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Address added successfully.");

            // Limpiar los campos de texto
            postalCodeField.setText("");
            streetField.setText("");
            cityIdField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Address ID:");

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
                findAddressUC.execute(id).ifPresentOrElse(
                        address -> showAddressDetails(address),
                        () -> JOptionPane.showMessageDialog(this, "Address not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Address");

        // Crear tabla para mostrar los datos
        String[] columnNames = { "ID", "Street", "PostalCode", "CityId" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // Modelo de la tabla
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Añadir la tabla al panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = createRoundedButton("Back");


        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        // Añadir el panel del botón debajo de la tabla
        panel.add(buttonPanel, BorderLayout.SOUTH);

        tableModel.setRowCount(0);

        // Obtener todos los Employee Roles y añadirlos a la tabla
        findAllAddressUC.execute().forEach(address -> {
            Object[] row = { address.getId(), address.getStreet(), address.getPostalCode(), address.getCityId()};
            tableModel.addRow(row);
        });

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Address ID:");
        JTextField idField = new JTextField(20);
        JLabel streetLabel = new JLabel("Enter new Address street:");
        JTextField streetField = new JTextField(20);
        JLabel postalCodeLabel = new JLabel("Enter new Address postalCode:");
        JTextField postalCodeField = new JTextField(20);
        JLabel cityIdLabel = new JLabel("Enter new Address cityId:");
        JTextField cityIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(postalCodeLabel);
        formPanel.add(postalCodeField);
        formPanel.add(streetLabel);
        formPanel.add(streetField);
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
                String street = postalCodeField.getText().trim();
                String postalCode = postalCodeField.getText().trim();
                String cityId = postalCodeField.getText().trim();

                if (street.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Address postalCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (postalCode.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Address postalCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cityId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Address postalCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                findAddressUC.execute(id).ifPresentOrElse(
                        Address -> {
                            Address.setStreet(street);
                            Address.setPostalCode(postalCode);
                            Address.setCityId(cityId);
                            updateAddressUC.execute(Address);
                            JOptionPane.showMessageDialog(this, "Address updated successfully.");
                            idField.setText("");

                            streetField.setText("");
                            postalCodeField.setText("");
                            cityIdField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Address not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter Address ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Address?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteAddressUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Address deleted successfully.");
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

    private void showAddressDetails(Address Address) {
        String details = String.format("""
                Address found:
                ID: %d
                Street: %s
                PostalCode: %s
                City: %s
                """, Address.getId(), Address.getStreet(), Address.getPostalCode(), Address.getCityId());
        JOptionPane.showMessageDialog(this, details, "Address Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new AddressAdapter();
    }

}
