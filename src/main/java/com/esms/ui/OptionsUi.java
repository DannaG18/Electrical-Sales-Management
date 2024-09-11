package com.esms.ui;

import java.awt.*;
import javax.swing.*;

import com.esms.customer.infrastructure.adapter.CustomerAdapter;
import com.esms.invoice.infrastructure.adapter.InvoiceUI;
import com.esms.login.infrastructure.controller.LoginController;

public class OptionsUi extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private LoginController loginController;

    public OptionsUi() {
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Select User Options");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel addPanel = createOperationPanel("Select Crud", "Search", createAddPanel());

        mainPanel.add(addPanel, "Search");

        add(mainPanel);
        cardLayout.show(mainPanel, "Search");

        setVisible(true);
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png");
        JLabel imageLabel = new JLabel(icon);

        headerPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 50, 60));

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
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton userRegistrationButton = createRoundedButton("User Registration");
        JButton generateInvoiceButton = createRoundedButton("Generate Invoice");
        JButton exitButton = createRoundedButton("Back");

        panel.add(userRegistrationButton);
        panel.add(generateInvoiceButton);
        panel.add(exitButton);

        userRegistrationButton.addActionListener(e -> new CustomerAdapter());
        generateInvoiceButton.addActionListener(e -> new InvoiceUI());
        exitButton.addActionListener(e -> {
            this.dispose(); // Cierra la ventana actual
            if (loginController != null) {
                loginController.setVisible(true); // Muestra el LoginController
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new OptionsUi();
    }
}

