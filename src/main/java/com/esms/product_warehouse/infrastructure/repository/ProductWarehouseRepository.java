package com.esms.product_warehouse.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.product_warehouse.domain.entity.ProductWarehouse;
import com.esms.product_warehouse.domain.service.ProductWarehouseService;

public class ProductWarehouseRepository implements ProductWarehouseService{
    private Connection connection;

    public ProductWarehouseRepository() {
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
    public void createProductWarehouse(ProductWarehouse productWarehouse) {
        String sql = "INSERT INTO product_warehouse ( product_id, warehouse_id, stock) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productWarehouse.getProductId());
            ps.setInt(2, productWarehouse.getWarehouseId());
            ps.setInt(3, productWarehouse.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductWarehouse(int id) {
        String sql = "DELETE FROM product_warehouse WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<ProductWarehouse> findProductWarehouse(int id) {
        String sql = "SELECT * FROM product_warehouse WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    ProductWarehouse productWarehouse = new ProductWarehouse(rs.getInt("id"), rs.getInt("product_id"),rs.getInt("warehouse_id"), rs.getInt("stock"));
                    return Optional.of(productWarehouse);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateProductWarehouse(ProductWarehouse productWarehouse) {
        String sql = "UPDATE product_warehouse SET product_id = ?, warehouse_id = ?, stock = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productWarehouse.getProductId());
            ps.setInt(2, productWarehouse.getWarehouseId());
            ps.setInt(3, productWarehouse.getStock());
            ps.setInt(4, productWarehouse.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductWarehouse> findAllProductWarehouse() {
        String sql = "SELECT * FROM product_warehouse";
        List<ProductWarehouse> productWarehouse = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    ProductWarehouse productWarehouse1 = new ProductWarehouse(rs.getInt("id"), rs.getInt("product_id"),rs.getInt("warehouse_id"), rs.getInt("stock"));
                    productWarehouse.add(productWarehouse1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productWarehouse;
    }
    
}
