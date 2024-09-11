package com.esms.discount.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.esms.discount.application.CreateDiscountUC;
import com.esms.discount.application.DeleteDiscountUC;
import com.esms.discount.application.FindAllDiscountUC;
import com.esms.discount.application.FindDiscountUC;
import com.esms.discount.application.UpdateDiscountUC;
import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;
import com.esms.discount.infrastructure.repository.DiscountRepository;
import com.esms.ui.CrudUi;

public class DiscountAdapter extends JFrame{
    private final DiscountService discountService;
    private final CreateDiscountUC createDiscountUC;
    private final FindDiscountUC findDiscountUC;
    private final FindAllDiscountUC findAllDiscountUC;
    private final UpdateDiscountUC updateDiscountUC;
    private final DeleteDiscountUC deleteDiscountUC;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    private CrudUi crudUi;

    public DiscountAdapter(){
        this.discountService = new DiscountRepository();
        this.createDiscountUC = new CreateDiscountUC(discountService);
        this.findDiscountUC = new FindDiscountUC(discountService);
        this.findAllDiscountUC = new FindAllDiscountUC(discountService);
        this.updateDiscountUC = new UpdateDiscountUC(discountService);
        this.deleteDiscountUC = new DeleteDiscountUC(discountService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu imagen
        setIconImage(windowIcon.getImage());
        setTitle("Discount Menu");
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
        JPanel addPanel = createOperationPanel("Add Discount", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Discount", "Search", createSearchPanel());
        JPanel findAllPanel = createOperationPanel("Find All Discount", "Find All", createFindAllPanel());
        JPanel updatePanel = createOperationPanel("Update Discount", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Discount", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Discount CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Discount");
        JButton searchButton = createRoundedButton("Search Discount");
        JButton findAllButton = createRoundedButton("Find All Employee Role");
        JButton updateButton = createRoundedButton("Update Discount");
        JButton deleteButton = createRoundedButton("Delete Discount");
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

        JLabel descriptionLabel = new JLabel("Enter description:");
        JTextField descriptionField = new JTextField(10);
        JLabel percentageLabel = new JLabel("Enter percentage:");
        JTextField percentageField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(percentageLabel);
        formPanel.add(percentageField);
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
            String description = descriptionField.getText().trim();
            double percentage = Double.parseDouble(percentageField.getText().trim());
        
            // Validar si los campos están vacíos
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Discount description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        
            // Crear nuevo Discount
            Discount discount = new Discount();
            discount.setDescription(description);
            discount.setPercentage(percentage);
        
            // Ejecutar caso de uso
            createDiscountUC.execute(discount);
        
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Discount added successfully.");
        
            // Limpiar los campos de texto
            percentageField.setText("");
            descriptionField.setText("");
        });
        

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
    
        JLabel idLabel = new JLabel("Enter Discount ID:");
    
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
                findDiscountUC.execute(id).ifPresentOrElse(
                    discount -> showDiscountDetails(discount),
                    () -> JOptionPane.showMessageDialog(this, "Discount not found.", "Error", JOptionPane.ERROR_MESSAGE)
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
        JPanel headerPanel = createHeaderPanel("Discount");
    
        // Crear tabla para mostrar los datos
        String[] columnNames = {"ID", "Description", "Percentage"};
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
            findAllDiscountUC.execute().forEach(discount -> {
                Object[] row = {discount.getId(), discount.getDescription(), discount.getPercentage(),};
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

        JLabel idLabel = new JLabel("Enter Discount ID:");
        JTextField idField = new JTextField(20);
        JLabel descriptionLabel = new JLabel("Enter new description:");
        JTextField descriptionField = new JTextField(20);
        JLabel percentageLabel = new JLabel("Enter new percentage:");
        JTextField percentageField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(percentageLabel);
        formPanel.add(percentageField);
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
                String description = percentageField.getText().trim();
                double percentage = Double.parseDouble(percentageField.getText().trim());

                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Discount percentage cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                findDiscountUC.execute(id).ifPresentOrElse(
                        Discount -> {
                            Discount.setDescription(description);
                            Discount.setPercentage(percentage);
                            updateDiscountUC.execute(Discount);
                            JOptionPane.showMessageDialog(this, "Discount updated successfully.");
                            idField.setText("");

                            descriptionField.setText("");
                            percentageField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Discount not found.", "Error", JOptionPane.ERROR_MESSAGE)
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

        JLabel idLabel = new JLabel("Enter Discount ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Discount?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteDiscountUC.execute(id);
                    JOptionPane.showMessageDialog(this, "Discount deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showDiscountDetails(Discount Discount) {
        String details = String.format("""
                Discount found:
                ID: %d
                Description: %s
                Percentage: %f%%
                """, Discount.getId(), Discount.getDescription(), Discount.getPercentage());
        JOptionPane.showMessageDialog(this, details, "Discount Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new DiscountAdapter();
    }

}
