package com.esms.purchase.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.purchase.domain.entity.Purchase;
import com.esms.purchase.domain.service.PurchaseService;

public class PurchaseRepository implements PurchaseService{
    private Connection connection;

    public PurchaseRepository() {
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
    public void createPurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase (purchase_date, total_amount, supplier_id, employee_id, branch_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, purchase.getPuchaseDate());
            ps.setDouble(2, purchase.getTotalAmount());
            ps.setInt(3, purchase.getSupplierId());
            ps.setInt(4, purchase.getEmployeeId());
            ps.setInt(5, purchase.getBranchId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePurchase(int id) {
        String sql = "DELETE FROM purchase WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Purchase> findPurchase(int id) {
                String sql = "SELECT * FROM purchase WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Purchase purchase = new Purchase(rs.getInt("id"), rs.getString("purchase_date"), rs.getDouble("total_amount"), rs.getInt("supplier_id"),rs.getInt("employee_id"), rs.getInt("branch_id"));
                    return Optional.of(purchase);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        String sql = "UPDATE purchase SET purchase_date = ?, total_amount = ?, supplier_id = ?, employee_id = ?, branch_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, purchase.getPuchaseDate());
            ps.setDouble(2, purchase.getTotalAmount());
            ps.setInt(3, purchase.getSupplierId());
            ps.setInt(4, purchase.getEmployeeId());
            ps.setInt(5, purchase.getBranchId());
            ps.setInt(6, purchase.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Purchase> findAllPurchase() {
        String sql = "SELECT * FROM purchase";
        List<Purchase> purchase = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Purchase purchase1 = new Purchase(rs.getInt("id"), rs.getString("purchase_date"), rs.getDouble("total_amount"), rs.getInt("supplier_id"),rs.getInt("employee_id"), rs.getInt("branch_id"));
                    purchase.add(purchase1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }
    
}
