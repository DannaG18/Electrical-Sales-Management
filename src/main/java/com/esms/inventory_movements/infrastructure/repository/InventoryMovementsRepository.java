package com.esms.inventory_movements.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.inventory_movements.domain.entity.InventoryMovements;
import com.esms.inventory_movements.domain.service.InventoryMovementsService;

public class InventoryMovementsRepository implements InventoryMovementsService {
    private Connection connection;

    public InventoryMovementsRepository() {
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
    public void createInventoryMovements(InventoryMovements inventoryMovements) {
        String sql = "INSERT INTO inventory_movements ( product_id, warehouse_id, movement_date, quantity, movement_type, employee_id ) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, inventoryMovements.getProductId());
            ps.setInt(2, inventoryMovements.getWarehouseId());
            ps.setString(3, inventoryMovements.getMovementDate());
            ps.setInt(4, inventoryMovements.getQuantity());
            ps.setString(5, inventoryMovements.getMovementType());
            ps.setInt(6, inventoryMovements.getEmployeeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventoryMovements(int id) {
        String sql = "DELETE FROM inventory_movements WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<InventoryMovements> findInventoryMovements(int id) {
        String sql = "SELECT * FROM inventory_movements WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    InventoryMovements inventoryMovements = new InventoryMovements(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("warehouse_id"),rs.getString("movement_date"), rs.getInt("quantity"), rs.getString("movement_type"), rs.getInt("employee_id"));
                    return Optional.of(inventoryMovements);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateInventoryMovements(InventoryMovements inventoryMovements) {
        String sql = "UPDATE inventory_movements SET product_id = ?, warehouse_id = ?, movement_date = ?, quantity = ?, movement_type = ?, employee_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, inventoryMovements.getProductId());
            ps.setInt(2, inventoryMovements.getWarehouseId());
            ps.setString(3, inventoryMovements.getMovementDate());
            ps.setInt(4, inventoryMovements.getQuantity());
            ps.setString(5, inventoryMovements.getMovementType());
            ps.setInt(6, inventoryMovements.getEmployeeId());
            ps.setInt(7, inventoryMovements.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryMovements> findAllInventoryMovements() {
        String sql = "SELECT * FROM inventory_movements";
        List<InventoryMovements> inventoryMovements = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    InventoryMovements inventoryMovements1 = new InventoryMovements(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("warehouse_id"),rs.getString("movement_date"), rs.getInt("quantity"), rs.getString("movement_type"), rs.getInt("employee_id"));
                    inventoryMovements.add(inventoryMovements1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryMovements;
    }
    
}
