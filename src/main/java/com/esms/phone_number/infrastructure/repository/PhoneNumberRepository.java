package com.esms.phone_number.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.phone_number.domain.entity.PhoneNumber;
import com.esms.phone_number.domain.service.PhoneNumberService;

public class PhoneNumberRepository implements PhoneNumberService{

    private Connection connection;

    public PhoneNumberRepository() {
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
    public void createPhoneNumber(PhoneNumber phone_number) {
        String sql = "INSERT INTO phone_number (country_code, area_code, phone_number) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, phone_number.getCountry_code());
            ps.setString(2, phone_number.getArea_code());
            ps.setString(3, phone_number.getPhone_number());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhoneNumber(int id) {
        String sql = "DELETE FROM phone_number WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }

    @Override
    public Optional<PhoneNumber> findPhoneNumber(int id) {
    String sql = "SELECT * FROM phone_number WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    PhoneNumber phone_number = new PhoneNumber(rs.getInt("id"), rs.getString("country_code"), rs.getString("area_code"), rs.getString("phone_number"));
                    return Optional.of(phone_number);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phone_number) {
        String sql = "UPDATE phone_number SET country_code = ?, area_code = ?, phone_number = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, phone_number.getCountry_code());
            ps.setString(2, phone_number.getArea_code());
            ps.setString(3, phone_number.getPhone_number());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PhoneNumber> findAllPhoneNumber() {
                String sql = "SELECT * FROM phone_number";
        List<PhoneNumber> phone_number = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    PhoneNumber phone_number1 = new PhoneNumber(rs.getInt("id"), rs.getString("country_code"), rs.getString("area_code"), rs.getString("phone_number"));
                    phone_number.add(phone_number1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phone_number;
    }
    
}
