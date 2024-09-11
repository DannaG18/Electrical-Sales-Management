package com.esms.warehouse.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.warehouse.domain.entity.Warehouse;
import com.esms.warehouse.domain.service.WarehouseService;

public class WarehouseRepository implements WarehouseService {
    private Connection connection;

    public WarehouseRepository() {
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
    public void createWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO warehouse (name, branch_id, city_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warehouse.getName());
            ps.setInt(2, warehouse.getBranchId());
            ps.setString(3, warehouse.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWarehouse(int id) {
        String sql = "DELETE FROM warehouse WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Warehouse> findWarehouse(int id) {
        String sql = "SELECT * FROM warehouse WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Warehouse warehouse = new Warehouse(rs.getInt("id"), rs.getString("name"),rs.getInt("branch_id"), rs.getString("city_id"));
                    return Optional.of(warehouse);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        String sql = "UPDATE warehouse SET name = ?, branch_id = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warehouse.getName());
            ps.setInt(2, warehouse.getBranchId());
            ps.setString(3, warehouse.getCityId());;
            ps.setInt(4, warehouse.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Warehouse> findAllWarehouse() {
        String sql = "SELECT * FROM warehouse";
        List<Warehouse> warehouse = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Warehouse warehouse1 = new Warehouse(rs.getInt("id"), rs.getString("name"), rs.getInt("branch_id"), rs.getString("city_id"));
                    warehouse.add(warehouse1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouse;
    }
    
}