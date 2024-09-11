package com.esms.employee_roles.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class EmployeeRoleRepo implements EmployeeRoleService{

    private Connection connection;
    
    public EmployeeRoleRepo() {
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
    public void createEmployeeRole(EmployeeRole employeeRole) {
        String sql = "INSERT INTO employee_role (role_name, description) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employeeRole.getRole_name());
            ps.setString(2, employeeRole.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<EmployeeRole> findEmployeeRole(int id) {
        String sql = "SELECT * FROM employee_role WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    EmployeeRole employeeRole = new EmployeeRole(rs.getInt("id"), rs.getString("role_name"), rs.getString("description"));
                    return Optional.of(employeeRole);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateEmployeeRole(EmployeeRole employeeRole) {
        String sql = "UPDATE employee_role SET role_name = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employeeRole.getRole_name());
            ps.setString(2, employeeRole.getDescription());
            ps.setInt(3, employeeRole.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmployeeRole> findAllEmployeeRole() {
        String sql = "SELECT * FROM employee_role";
        List<EmployeeRole> employeeRole = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    EmployeeRole employeeRole1 = new EmployeeRole(rs.getInt("id"), rs.getString("role_name"), rs.getString("description"));
                    employeeRole.add(employeeRole1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeRole;
    }


    @Override
    public void deleteEmployeeRole(int id) {
        String sql = "DELETE FROM employee_role WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
}
