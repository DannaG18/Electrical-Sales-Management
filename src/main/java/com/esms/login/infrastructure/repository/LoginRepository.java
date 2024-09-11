package com.esms.login.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.esms.login.domain.entity.LoginUsers;
import com.esms.login.domain.service.LoginService;

public class LoginRepository implements LoginService{
    private Connection connection;

    public LoginRepository() {
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
    public Optional<LoginUsers> loginAuthenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                boolean enabled = rs.getBoolean("enabled");
                return Optional.of(new LoginUsers(id, user, pass, enabled));
            } else {
                JOptionPane.showMessageDialog(null,"Please, Check and Try Again", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean loginRoleUser(int id) {
        String query = "SELECT r.name FROM user_roles ur JOIN roles r ON r.id = ur.role_id WHERE ur.user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("name").equals("admin")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

