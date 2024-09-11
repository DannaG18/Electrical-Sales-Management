package com.esms.customer.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.customer.domain.entity.Customer;
import com.esms.customer.domain.service.CustomerService;

public class CustomerRepository implements CustomerService{
    private Connection connection;

    public CustomerRepository(){
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
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, email, phone_id, city_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setInt(3, customer.getPhoneId());
            ps.setString(4, customer.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Customer> findCustomer(int id) {
                String sql = "SELECT * FROM customer WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("city_id"));
                    return Optional.of(customer);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?,  email = ?, phone_id = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setInt(3, customer.getPhoneId());
            ps.setString(4, customer.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAllCustomer() {
        String sql = "SELECT * FROM customer";
        List<Customer> customer = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Customer customer1 = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone_id"), rs.getString("city_id"));
                    customer.add(customer1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
}
