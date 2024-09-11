package com.esms.phone_number.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.esms.phone_number.application.CreatePhoneNumberUC;
import com.esms.phone_number.application.DeletePhoneNumberUC;
import com.esms.phone_number.application.FindAllPhoneNumberUC;
import com.esms.phone_number.application.FindPhoneNumberUC;
import com.esms.phone_number.application.UpdatePhoneNumberUC;
import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;
import com.esms.phone_number.infrastructure.repository.PhoneNumberRepository;
import com.esms.ui.CrudUi;

public class PhoneNumberAdapter extends JFrame {
    private final PhoneNumberService phoneNumberService;
    private final CreatePhoneNumberUC createPhoneNumberUC;
    private final UpdatePhoneNumberUC updatePhoneNumberUC;
    private final FindAllPhoneNumberUC findAllPhoneNumberUC;
    private final FindPhoneNumberUC findPhoneNumberUC;
    private final DeletePhoneNumberUC deletePhoneNumberUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public PhoneNumberAdapter() {
        this.phoneNumberService = new PhoneNumberRepository();
        this.createPhoneNumberUC = new CreatePhoneNumberUC(phoneNumberService);
        this.updatePhoneNumberUC = new UpdatePhoneNumberUC(phoneNumberService);
        this.findAllPhoneNumberUC = new FindAllPhoneNumberUC(phoneNumberService);
        this.findPhoneNumberUC = new FindPhoneNumberUC(phoneNumberService);
        this.deletePhoneNumberUC = new DeletePhoneNumberUC(phoneNumberService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu
                                                                                     // imagen
        setIconImage(windowIcon.getImage());
        setTitle("Phone Number Menu");
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
        JPanel addPanel = createOperationPanel("Add PhoneNumber", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search PhoneNumber", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All PhoneNumber", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update PhoneNumber", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete PhoneNumber", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("PhoneNumber CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add PhoneNumber");
        JButton searchButton = createRoundedButton("Search PhoneNumber");
        JButton findAllButton = createRoundedButton("Find All Employee Role");
        JButton updateButton = createRoundedButton("Update PhoneNumber");
        JButton deleteButton = createRoundedButton("Delete PhoneNumber");
        JButton backButton = createRoundedButton("Back");
     

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(findAllButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        marginPanel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(marginPanel, BorderLayout.CENTER);

        // Action Listeners para cada botón
        addButton.addActionListener(e -> cardLayout.show(mainPanel, "Add"));
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
        findAllButton.addActionListener(e -> cardLayout.show(mainPanel, "FindAll"));
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "Update"));
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "Delete"));
        backButton.addActionListener(e -> { this.dispose(); crudUi.setVisible(true); }); 

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

        JLabel countryCodeLabel = new JLabel("Enter Country Code:");
        JTextField countryCodeField = new JTextField(10);
        JLabel areaCodeLabel = new JLabel("Enter Area Code:");
        JTextField areaCodeField = new JTextField(10);
        JLabel phone_numberLabel = new JLabel("Enter Phone Number:");
        JTextField phone_numberField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(countryCodeLabel);
        formPanel.add(countryCodeField);
        formPanel.add(areaCodeLabel);
        formPanel.add(areaCodeField);
        formPanel.add(phone_numberLabel);
        formPanel.add(phone_numberField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String countryCode = countryCodeField.getText().trim();
            String areaCode = areaCodeField.getText().trim();
            String phone_number = phone_numberField.getText().trim();

            // Validar si los campos están vacíos
            if (countryCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PhoneNumber name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (areaCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PhoneNumber areaCode cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (phone_number.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PhoneNumber areaCode cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo PhoneNumber
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setCountry_code(countryCode);
            phoneNumber.setArea_code(areaCode);
            phoneNumber.setPhone_number(phone_number);

            // Ejecutar caso de uso
            createPhoneNumberUC.execute(phoneNumber);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "PhoneNumber added successfully.");

            // Limpiar los campos de texto
            countryCodeField.setText("");
            areaCodeField.setText("");
            phone_numberField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter PhoneNumber ID:");

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
                findPhoneNumberUC.execute(id).ifPresentOrElse(
                        phoneNumber -> showPhoneNumberDetails(phoneNumber),
                        () -> JOptionPane.showMessageDialog(this, "PhoneNumber not found.", "Error",
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
        JPanel headerPanel = createHeaderPanel("Employee Roles");
    
        // Crear tabla para mostrar los datos
        String[] columncountryCodes = { "ID", "Role countryCode", "areaCode", "Phone Number" };
        DefaultTableModel tableModel = new DefaultTableModel(columncountryCodes, 0); // Modelo de la tabla
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
            findAllPhoneNumberUC.execute().forEach(phoneNumber -> {
                Object[] row = { phoneNumber.getId(), phoneNumber.getCountry_code(), phoneNumber.getArea_code(), phoneNumber.getPhone_number() };
                tableModel.addRow(row);
            });
        });
    
        // Botón para regresar
        JButton backButton = createRoundedButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);  // Añadir el botón "Buscar"
        buttonPanel.add(backButton);    // Añadir el botón "Back"
    
        // Añadir el panel de botones debajo de la tabla
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        // Cargar todos los Employee Roles
        tableModel.setRowCount(0);

    
        return panel;
    }
    

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter PhoneNumber ID:");
        JTextField idField = new JTextField(20);
        JLabel countryCodeLabel = new JLabel("Enter new countryCode:");
        JTextField countryCodeField = new JTextField(20);
        JLabel areaCodeLabel = new JLabel("Enter new areaCode:");
        JTextField areaCodeField = new JTextField(20);
        JLabel phone_numberLabel = new JLabel("Enter new phone_number:");
        JTextField phone_numberField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(countryCodeLabel);
        formPanel.add(countryCodeField);
        formPanel.add(areaCodeLabel);
        formPanel.add(areaCodeField);
        formPanel.add(phone_numberLabel);
        formPanel.add(phone_numberField);
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
                String countryCode = countryCodeField.getText().trim();
                String areaCode = areaCodeField.getText().trim();
                String phone_number = phone_numberField.getText().trim();

                if (countryCode.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "PhoneNumber countryCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (areaCode.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "PhoneNumber countryCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (phone_number.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "PhoneNumber countryCode cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                findPhoneNumberUC.execute(id).ifPresentOrElse(
                        PhoneNumber -> {
                            PhoneNumber.setCountry_code(countryCode);
                            PhoneNumber.setArea_code(areaCode);
                            PhoneNumber.setPhone_number(phone_number);

                            updatePhoneNumberUC.execute(PhoneNumber);
                            JOptionPane.showMessageDialog(this, "PhoneNumber updated successfully.");
                            
                            idField.setText("");
                            countryCodeField.setText("");
                            areaCodeField.setText("");
                            phone_numberField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "PhoneNumber not found.", "Error",
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

        JLabel idLabel = new JLabel("Enter PhoneNumber ID:");
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
                        "Are you sure you want to delete this PhoneNumber?", "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deletePhoneNumberUC.execute(id);
                    JOptionPane.showMessageDialog(this, "PhoneNumber deleted successfully.");
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

    private void showPhoneNumberDetails(PhoneNumber PhoneNumber) {
        String details = String.format("""
                PhoneNumber found:
                ID: %d
                CountryCode: %s
                AreaCode: %s
                PhoneNumber: %s
                """, PhoneNumber.getId(), PhoneNumber.getCountry_code(), PhoneNumber.getArea_code(), PhoneNumber.getPhone_number());
        JOptionPane.showMessageDialog(this, details, "PhoneNumber Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new PhoneNumberAdapter();
    }

}