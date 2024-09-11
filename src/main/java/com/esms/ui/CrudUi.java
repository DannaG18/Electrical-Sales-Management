package com.esms.ui;

import javax.swing.*;

import com.esms.branch.infrastructure.adapter.BranchAdapter;
import com.esms.category.infrastructure.adapter.CategoryAdapter;
import com.esms.city.infrastructure.adapter.CityAdapter;
import com.esms.country.infrastructure.adapters.CountryAdapter;
import com.esms.customer.infrastructure.adapter.CustomerAdapter;
import com.esms.discount.infrastructure.adapter.DiscountAdapter;
import com.esms.employee.infrastructure.adapter.EmployeeAdapter;
import com.esms.employee_roles.infrastructure.adapters.EmployeeRoleAdapter;
import com.esms.inventory_movements.infrastructure.adapter.InventoryMovementsAdapter;
import com.esms.phone_number.infrastructure.adapter.PhoneNumberAdapter;
import com.esms.product.infrastructure.adapter.ProductAdapter;
import com.esms.product_supplier.infrastructure.adapter.ProductSupplierAdapter;
import com.esms.product_warehouse.infrastructure.adapter.ProductWarehouseAdapter;
import com.esms.purchase.infrastructure.adapter.PurchaseAdapter;
import com.esms.purchase_details.infrastructure.adapter.PurchaseDetailsAdapter;
import com.esms.sale.infrastructure.adapter.SaleAdapter;
import com.esms.sale_details.infrastructure.adapter.SalesDetailsAdapter;
import com.esms.supplier.infrastructure.adapter.SupplierAdapter;
import com.esms.warehouse.infrastructure.adapter.WarehouseAdapter;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class CrudUi extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public CrudUi() {
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Select Crud");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel addPanel = createOperationPanel("Select Crud", "Search", createAddPanel());

        mainPanel.add(addPanel, "Search");

        add(mainPanel);
        cardLayout.show(mainPanel, "Menu");

        setVisible(true);
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png");
        JLabel imageLabel = new JLabel(icon);

        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(imageLabel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createOperationPanel(String title, String cardName, JPanel operationPanel) {
        JPanel panel = new JPanel(new BorderLayout());

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

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        List<String> op = new ArrayList<>();
        op.add("Select an option");
        op.add("Employee Role CRUD");
        op.add("Country CRUD");
        op.add("Phone Number CRUD");
        op.add("Category CRUD");
        op.add("Discount CRUD");
        op.add("City CRUD");
        op.add("Branch CRUD");
        op.add("Supplier CRUD");
        op.add("Customer CRUD");
        op.add("Product CRUD");
        op.add("Product Supplier CRUD");
        op.add("Warehouse CRUD");
        op.add("Product Warehouse CRUD");
        op.add("Employee CRUD");
        op.add("Inventory Movements");
        op.add("Purchase CRUD");
        op.add("Purchase Details CRUD");
        op.add("Sale CRUD");
        op.add("Sale Details CRUD");

        String[] namesArray = op.toArray(new String[0]);
        JComboBox<String> nameComboBox = new JComboBox<>(namesArray);
        nameComboBox.setPreferredSize(new Dimension(100, 30));
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameComboBox);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = (String) nameComboBox.getSelectedItem();

            switch (name) {
                case "Employee Role CRUD":
                    new EmployeeRoleAdapter();
                    break;
                case "Country CRUD":
                    new CountryAdapter();
                    break;
                case "Phone Number CRUD":
                    new PhoneNumberAdapter();
                    break;
                case "Category CRUD":
                    new CategoryAdapter();
                    break;
                case "Discount CRUD":
                    new DiscountAdapter();
                    break;
                case "City CRUD":
                    new CityAdapter();
                    break;
                case "Branch CRUD":
                    new BranchAdapter();
                    break;
                case "Supplier CRUD":
                    new SupplierAdapter();
                    break;
                case "Customer CRUD":
                    new CustomerAdapter();
                    break;
                case "Product CRUD":
                    new ProductAdapter();
                    break;
                case "Product Supplier CRUD":
                    new ProductSupplierAdapter();
                    break;
                case "Warehouse CRUD":
                    new WarehouseAdapter();
                    break;
                case "Product Warehouse CRUD":
                    new ProductWarehouseAdapter();
                    break;
                case "Employee CRUD":
                    new EmployeeAdapter();
                    break;
                case "Inventory Movements":
                    new InventoryMovementsAdapter();
                    break;
                case "Purchase CRUD":
                    new PurchaseAdapter();
                    break;
                case "Purchase Details CRUD":
                    new PurchaseDetailsAdapter();
                    break;
                case "Sale CRUD":
                    new SaleAdapter();
                    break;
                case "Sale Details CRUD":
                    new SalesDetailsAdapter();
                    break;
                case "Select an option":
                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid option.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

        // backButton.addActionListener(e -> new LoginController());

        return panel;
    }

    public static void main(String[] args) {
        new CrudUi();
    }
}