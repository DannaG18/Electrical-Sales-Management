package com.esms.invoice.infrastructure.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;
import com.esms.invoice.domain.entity.Invoice;
import com.esms.invoice.domain.entity.SaleDetailsDto;
import com.esms.invoice.infrastructure.repository.InvoiceRepository;
import com.esms.login.infrastructure.controller.LoginController;

public class InvoiceUI extends JFrame {
    
    private InvoiceRepository invoiceRepository;
    private JTextField idField;
    private JTextArea invoiceDetailsArea;
    private JTable saleDetailsTable;
    private DefaultTableModel tableModel;

    private LoginController loginController;

    public InvoiceUI() {
        invoiceRepository = new InvoiceRepository();

        setTitle("Invoice Details");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usar GridBagLayout para una mejor distribución
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Panel de búsqueda
        JPanel searchPanel = createSearchPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(searchPanel, gbc);

        // Área de detalles de factura
        invoiceDetailsArea = new JTextArea(10, 40);
        invoiceDetailsArea.setEditable(false);
        invoiceDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane detailsScrollPane = new JScrollPane(invoiceDetailsArea);
        detailsScrollPane.setBorder(BorderFactory.createTitledBorder("Invoice Details"));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(detailsScrollPane, gbc);

        // Tabla de detalles de venta
        saleDetailsTable = new JTable();
        tableModel = new DefaultTableModel(new String[]{"Product Name", "Quantity", "Unit Price"}, 0);
        saleDetailsTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(saleDetailsTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Sale Details"));
        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(tableScrollPane, gbc);

        JButton backButton = createRoundedButton("Back");
        searchPanel.add(backButton);
        backButton.addActionListener(e -> {
            this.dispose(); // Cierra la ventana actual
            if (loginController != null) {
                loginController.setVisible(true); // Muestra el LoginController
            }
        });

        setVisible(true); // Asegúrate de que la ventana se muestre
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

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Invoice"));
        
        JLabel idLabel = new JLabel("Invoice ID:");
        idField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(66, 134, 244));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        searchPanel.add(idLabel);
        searchPanel.add(idField);
        searchPanel.add(searchButton);

        searchButton.addActionListener(e -> searchInvoice());

        return searchPanel;
    }

    private void searchInvoice() {
        String invoiceIdText = idField.getText();
        try {
            int invoiceId = Integer.parseInt(invoiceIdText);
            Optional<Invoice> invoiceOpt = invoiceRepository.findInvoiceById(invoiceId);
            if (invoiceOpt.isPresent()) {
                displayInvoiceDetails(invoiceOpt.get());
            } else {
                showErrorMessage("Invoice not found");
            }
        } catch (NumberFormatException ex) {
            showErrorMessage("Invalid ID format");
        }
    }

    private void displayInvoiceDetails(Invoice invoice) {
        // Mostrar los detalles generales de la factura
        StringBuilder invoiceDetails = new StringBuilder();
        invoiceDetails.append(String.format("%-20s %s%n", "Invoice ID:", invoice.getSaleId()));
        invoiceDetails.append(String.format("%-20s %s%n", "Sale Date:", invoice.getSaleDate()));
        invoiceDetails.append(String.format("%-20s $%.2f%n", "Total Amount:", invoice.getTotalAmount()));
        invoiceDetails.append(String.format("%-20s %s%n", "Customer Name:", invoice.getCustomerName()));
        invoiceDetails.append(String.format("%-20s %s%n", "Customer Email:", invoice.getCustomerEmail()));
        invoiceDetails.append(String.format("%-20s %s %s%n", "Employee:", invoice.getEmployeeFirstName(), invoice.getEmployeeLastName()));
        invoiceDetails.append(String.format("%-20s %s%n", "Branch:", invoice.getBranchName()));

        invoiceDetailsArea.setText(invoiceDetails.toString());

        // Limpiar la tabla antes de agregar nuevos detalles
        tableModel.setRowCount(0);

        // Mostrar los detalles de los productos vendidos
        for (SaleDetailsDto detail : invoice.getSaleDetails()) {
            tableModel.addRow(new Object[]{
                detail.getProductName(),
                detail.getQuantity(),
                String.format("$%.2f", detail.getUnitPrice())
            });
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new InvoiceUI();
    }
}