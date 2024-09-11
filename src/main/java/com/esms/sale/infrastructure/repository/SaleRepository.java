package com.esms.sale.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.sale.domain.entity.Sale;
import com.esms.sale.domain.service.SaleService;

public class SaleRepository implements SaleService{
    private Connection connection;

    public SaleRepository() {
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
    public void createSale(Sale sale) {
        String sql = "INSERT INTO sale (sale_date, total_amount, customer_id, employee_id, branch_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sale.getSaleDate());
            ps.setDouble(2, sale.getTotalAmount());
            ps.setInt(3, sale.getCustomerId());
            ps.setInt(4, sale.getEmployeeId());
            ps.setInt(5, sale.getBranchId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSale(int id) {
        String sql = "DELETE FROM sale WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Sale> findSale(int id) {
        String sql = "SELECT * FROM sale WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Sale sale = new Sale(rs.getInt("id"), rs.getString("sale_date"), rs.getDouble("total_amount"), rs.getInt("customer_id"),rs.getInt("employee_id"), rs.getInt("branch_id"));
                    return Optional.of(sale);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSale(Sale sale) {
        String sql = "UPDATE sale SET sale_date = ?, total_amount = ?, customer_id = ?, employee_id = ?, branch_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sale.getSaleDate());
            ps.setDouble(2, sale.getTotalAmount());
            ps.setInt(3, sale.getCustomerId());
            ps.setInt(4, sale.getEmployeeId());
            ps.setInt(5, sale.getBranchId());
            ps.setInt(6, sale.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sale> findAllSale() {
                String sql = "SELECT * FROM sale";
        List<Sale> sale = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Sale sale1 = new Sale(rs.getInt("id"), rs.getString("sale_date"), rs.getDouble("total_amount"), rs.getInt("customer_id"),rs.getInt("employee_id"), rs.getInt("branch_id"));
                    sale.add(sale1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }
    
}
