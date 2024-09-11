package com.esms.product_supplier.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.product_supplier.domain.entity.ProductSupplier;
import com.esms.product_supplier.domain.service.ProductSupplierService;

public class ProductSupplierRepository implements ProductSupplierService{
    private Connection connection;

    public ProductSupplierRepository(){
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
    public void createProductSupplier(ProductSupplier productSupplier) {
        String sql = "INSERT INTO product_supplier (product_id, supplier_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productSupplier.getProductId());
            ps.setInt(2, productSupplier.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductSupplier(int productId, int supplierId) {
        String sql = "DELETE FROM product_supplier WHERE product_id = ? AND supplier_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, supplierId);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<ProductSupplier> findProductSupplier(int productId, int supplierId) {
        String sql = """
            SELECT ps.product_id, p.name AS product_name, ps.supplier_id, s.name AS supplier_name 
            FROM product_supplier ps
            JOIN product p ON p.id = ps.product_id
            JOIN supplier s ON s.id = ps.supplier_id
            WHERE ps.product_id = ? AND ps.supplier_id = ?
        """;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    ProductSupplier productSupplier = new ProductSupplier(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),  // Aquí usas el alias 'product_name'
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"));  // Aquí usas el alias 'supplier_name'
                    return Optional.of(productSupplier);
                }
                    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateProductSupplier(ProductSupplier productSupplier) {
        String sql = "UPDATE product_supplier SET product_id = ?, supplier_id = ? WHERE product_id = ? AND supplier_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // Establecer los nuevos valores
            ps.setInt(1, productSupplier.getProductId());
            ps.setInt(2, productSupplier.getSupplierId());
            // Usar los valores originales para la condición WHERE
            ps.setInt(3, productSupplier.getOriginalProductId());
            ps.setInt(4, productSupplier.getOriginalSupplierId());
    
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public List <ProductSupplier> findAllProductSupplier() {
        String sql = """
            SELECT ps.product_id, p.name AS product_name, ps.supplier_id, s.name AS supplier_name 
            FROM product_supplier ps
            JOIN product p ON p.id = ps.product_id
            JOIN supplier s ON s.id = ps.supplier_id
        """;
        List<ProductSupplier> productSupplier = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    ProductSupplier productSupplier1 = new ProductSupplier(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),  // Aquí usas el alias 'product_name'
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name")); 
                    productSupplier.add(productSupplier1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSupplier;
    }
    
}
