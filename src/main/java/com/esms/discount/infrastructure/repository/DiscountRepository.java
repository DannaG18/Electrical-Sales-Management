package com.esms.discount.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.discount.domain.entity.Discount;
import com.esms.discount.domain.service.DiscountService;

public class DiscountRepository implements DiscountService{
    private Connection connection;

    public DiscountRepository() {
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
    public void createDiscount(Discount discount) {
        String sql = "INSERT INTO discount (description, percentage) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, discount.getDescription());
            ps.setDouble(2, discount.getPercentage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDiscount(int id) {
        String sql = "DELETE FROM discount WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public Optional<Discount> findDiscount(int id) {
        String sql = "SELECT * FROM discount WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Discount discount = new Discount(rs.getInt("id"), rs.getString("description"), rs.getDouble("percentage"));
                    return Optional.of(discount);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateDiscount(Discount discount) {
        String sql = "UPDATE discount SET description = ?, percentage = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, discount.getDescription());
            ps.setDouble(2, discount.getPercentage());
            ps.setInt(3, discount.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Discount> findAllDiscount() {
                String sql = "SELECT * FROM discount";
        List<Discount> discount = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Discount discount1 = new Discount(rs.getInt("id"), rs.getString("description"), rs.getDouble("percentage"));
                    discount.add(discount1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }
    
}
