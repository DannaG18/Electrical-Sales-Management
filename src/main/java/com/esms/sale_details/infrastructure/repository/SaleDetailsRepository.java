package com.esms.sale_details.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.sale_details.domain.entity.SaleDetails;
import com.esms.sale_details.domain.service.SaleDetailsService;

public class SaleDetailsRepository implements SaleDetailsService{
    private Connection connection;

    public SaleDetailsRepository() {
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
    public void createSaleDetails(SaleDetails saleDetails) {

        String sql = "INSERT INTO sale_details (sale_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, saleDetails.getSaleId());
            ps.setInt(2, saleDetails.getProductId());
            ps.setInt(3, saleDetails.getQuantity());
            ps.setDouble(4, saleDetails.getUnitPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSaleDetails(int id) {

        String sql = "DELETE FROM sale_details WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<SaleDetails> findSaleDetails(int id) {
                String sql = "SELECT * FROM sale_details WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    SaleDetails saleDetails = new SaleDetails(rs.getInt("id"), rs.getInt("sale_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unit_price"));
                    return Optional.of(saleDetails);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSaleDetails(SaleDetails saleDetails) {
        String sql = "UPDATE sale_details SET sale_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, saleDetails.getSaleId());
            ps.setInt(2, saleDetails.getProductId());
            ps.setInt(3, saleDetails.getQuantity());
            ps.setDouble(4, saleDetails.getUnitPrice());
            ps.setInt(5, saleDetails.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SaleDetails> findAllSaleDetails() {
        String sql = "SELECT * FROM sale_details";
        List<SaleDetails> saleDetails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    SaleDetails saleDetails1 = new SaleDetails(rs.getInt("id"), rs.getInt("sale_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unit_price"));
                    saleDetails.add(saleDetails1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleDetails;
    }
    
}
