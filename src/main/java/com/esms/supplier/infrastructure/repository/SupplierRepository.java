package com.esms.supplier.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.supplier.domain.entity.Supplier;
import com.esms.supplier.domain.service.SupplierService;

public class SupplierRepository implements SupplierService{
    private Connection connection;

    public SupplierRepository() {
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
    public void createSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier (name, email, phone_id, city_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setInt(3, supplier.getPhoneId());
            ps.setString(4, supplier.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSupplier(int id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Supplier> findSupplier(int id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Supplier supplier = new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("city_id"));
                    return Optional.of(supplier);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET name = ?,  email = ?, phone_id = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setInt(3, supplier.getPhoneId());
            ps.setString(4, supplier.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> findAllSupplier() {
        String sql = "SELECT * FROM supplier";
        List<Supplier> supplier = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Supplier supplier1 = new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("city_id"));
                    supplier.add(supplier1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }
    
}
