package com.esms.country.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.country.domain.entity.Country;
import com.esms.country.domain.service.CountryService;

public class CountryRepository implements CountryService{
    private Connection connection;

    public CountryRepository() {
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
    public void createCountry(Country country) {
        String sql = "INSERT INTO country (id, name) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country.getId());
            ps.setString(2, country.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String id) {
        String sql = "DELETE FROM country WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }

    @Override
    public Optional<Country> findCountry(String id) {
        String sql = "SELECT * FROM country WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Country country = new Country(rs.getString("id"), rs.getString("name"));
                    return Optional.of(country);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCountry(Country country) {
        String sql = "UPDATE country SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country.getName());
            ps.setString(2, country.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Country> findAllCountry() {
        String sql = "SELECT * FROM country";
        List<Country> country = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Country country1 = new Country(rs.getString("id"), rs.getString("name"));
                    country.add(country1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
}
