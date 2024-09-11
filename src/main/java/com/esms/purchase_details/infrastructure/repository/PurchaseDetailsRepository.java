package com.esms.purchase_details.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.purchase_details.domain.entity.PurchaseDetails;
import com.esms.purchase_details.domain.service.PurchaseDetailsService;


public class PurchaseDetailsRepository implements PurchaseDetailsService{
    private Connection connection;

    public PurchaseDetailsRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createPurchaseDetails(PurchaseDetails purchaseDetails) {
        String sql = "INSERT INTO purchase_details (purchase_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, purchaseDetails.getPurchaseId());
            ps.setInt(2, purchaseDetails.getProductId());
            ps.setInt(3, purchaseDetails.getQuantity());
            ps.setDouble(4, purchaseDetails.getUnitPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePurchaseDetails(int id) {
        String sql = "DELETE FROM purchase_details WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<PurchaseDetails> findPurchaseDetails(int id) {
        String sql = "SELECT * FROM purchase_details WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    PurchaseDetails purchaseDetails = new PurchaseDetails(rs.getInt("id"), rs.getInt("purchase_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unit_price"));
                    return Optional.of(purchaseDetails);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updatePurchaseDetails(PurchaseDetails purchaseDetails) {
        String sql = "UPDATE purchase_details SET purchase_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, purchaseDetails.getPurchaseId());
            ps.setInt(2, purchaseDetails.getProductId());
            ps.setInt(3, purchaseDetails.getQuantity());
            ps.setDouble(4, purchaseDetails.getUnitPrice());
            ps.setInt(5, purchaseDetails.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PurchaseDetails> findAllPurchaseDetails() {
        String sql = "SELECT * FROM purchase_details";
        List<PurchaseDetails> purchaseDetails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    PurchaseDetails purchaseDetails1 = new PurchaseDetails(rs.getInt("id"), rs.getInt("purchase_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unit_price"));
                    purchaseDetails.add(purchaseDetails1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseDetails;
    }
    
}
