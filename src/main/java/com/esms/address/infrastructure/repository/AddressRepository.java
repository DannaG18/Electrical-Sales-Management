package com.esms.address.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.address.domain.entity.Address;
import com.esms.address.domain.service.AddressService;

public class AddressRepository implements AddressService{
    private Connection connection;

    public AddressRepository() {
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
    public void createAddress(Address address) {
        String sql = "INSERT INTO address (street, postal_code, city_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getPostalCode());
            ps.setString(3, address.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAddress(int id) {
        String sql = "DELETE FROM address WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Address> findAddress(int id) {
        String sql = "SELECT * FROM address WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Address address = new Address(rs.getInt("id"), rs.getString("street"), rs.getString("postal_code"), rs.getString("city_id"));
                    return Optional.of(address);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateAddress(Address address) {
        String sql = "UPDATE address SET street = ?, postal_code = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getPostalCode());
            ps.setString(3, address.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findAllAddress() {
        String sql = "SELECT * FROM address";
        List<Address> address = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Address address1 = new Address(rs.getInt("id"), rs.getString("street"), rs.getString("postal_code"), rs.getString("city_id"));
                    address.add(address1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
    
}
