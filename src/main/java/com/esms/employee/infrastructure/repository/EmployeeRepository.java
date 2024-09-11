package com.esms.employee.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.employee.domain.entity.Employee;
import com.esms.employee.domain.service.EmployeeService;

public class EmployeeRepository implements EmployeeService{
    private Connection connection;

    public EmployeeRepository() {
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
    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employee (first_name, last_name, email, phone_id, hire_date, salary, role_id, branch_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getPhoneId());
            ps.setString(5, employee.getHireDate());
            ps.setDouble(6, employee.getSalary());
            ps.setInt(7, employee.getRoleId());
            ps.setInt(8, employee.getBranchId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Employee> findEmployee(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Employee employee = new Employee(rs.getInt("id"), rs.getString("first_name"),  rs.getString("last_name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("hire_date"), rs.getDouble("salary"), rs.getInt("role_id"), rs.getInt("branch_id"));
                    return Optional.of(employee);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone_id = ?, hire_date = ?, salary = ?, role_id = ?, branch_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getPhoneId());
            ps.setString(5, employee.getHireDate());
            ps.setDouble(6, employee.getSalary());
            ps.setInt(7, employee.getRoleId());
            ps.setInt(8, employee.getBranchId());
            ps.setInt(9, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAllEmployee() {
        String sql = "SELECT * FROM employee";
        List<Employee> employee = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Employee employee1 = new Employee(rs.getInt("id"), rs.getString("first_name"),  rs.getString("last_name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("hire_date"), rs.getDouble("salary"), rs.getInt("role_id"), rs.getInt("branch_id"));
                    employee.add(employee1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
}
