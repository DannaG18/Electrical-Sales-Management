package com.esms.city.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.city.domain.entity.City;
import com.esms.city.domain.service.CityService;

public class CityRepository implements CityService{
    private Connection connection;

    public CityRepository() {
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
    public void createCity(City city) {
        String sql = "INSERT INTO city (id, name, country_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, city.getId());
            ps.setString(2, city.getName());
            ps.setString(3, city.getCountryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String id) {
        String sql = "DELETE FROM city WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }

    @Override
    public Optional<City> findCity(String id) {
        String sql = "SELECT * FROM city WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    City city = new City(rs.getString("id"), rs.getString("name"), rs.getString("country_id"));
                    return Optional.of(city);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCity(City city) {
        String sql = "UPDATE city SET name = ?, country_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, city.getName());
            ps.setString(2, city.getCountryId());
            ps.setString(3, city.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAllCity() {
        String sql = "SELECT * FROM city";
        List<City> city = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    City city1 = new City(rs.getString("id"), rs.getString("name"), rs.getString("country_id"));
                    city.add(city1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }
    
}
