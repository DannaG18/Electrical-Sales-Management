package com.esms.login.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import javax.swing.JOptionPane;

import com.esms.employee_roles.infrastructure.adapters.EmployeeRoleAdapter;
import com.esms.login.domain.entity.LoginUsers;
import com.esms.login.domain.service.LoginService;

public class LoginRepository implements LoginService {
    private Connection connection;

    // Constructor para inicializar la conexión a la base de datos
    public LoginRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace(); // Considera lanzar una excepción personalizada o manejarlo de otra forma
        }
    }

    // Método para autenticar usuario
    @Override
    public Optional<LoginUsers> loginAuthenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) { // try-with-resources para cerrar PreparedStatement automáticamente
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) { // try-with-resources para cerrar ResultSet automáticamente
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String user = rs.getString("username");
                    String pass = rs.getString("password");
                    boolean enabled = rs.getBoolean("enabled");
                    return Optional.of(new LoginUsers(id, user, pass, enabled));
                } else {
                    JOptionPane.showMessageDialog(null, "Please, Check and Try Again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Método para obtener el nombre del rol del usuario según su ID
    @Override
    public Optional<String> getRoleName(int userId) {
        String query = "SELECT r.name FROM roles r " +
                       "JOIN user_roles ur ON r.id = ur.role_id " +
                       "WHERE ur.user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) { // Usa la conexión de clase
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Cierra la conexión al finalizar el uso del repositorio
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


