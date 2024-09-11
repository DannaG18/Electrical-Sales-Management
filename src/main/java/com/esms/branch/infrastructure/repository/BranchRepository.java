package com.esms.branch.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.esms.branch.domain.entity.Branch;
import com.esms.branch.domain.service.BranchService;

public class BranchRepository implements BranchService{
    private Connection connection;

    public BranchRepository() {
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
    public void createBranch(Branch branch) {
        String sql = "INSERT INTO branch (name, city_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, branch.getName());
            ps.setString(2, branch.getCityId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBranch(int id) {
        String sql = "DELETE FROM branch WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Branch> findBranch(int id) {
        String sql = "SELECT * FROM branch WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Branch branch = new Branch(rs.getInt("id"), rs.getString("name"), rs.getString("city_id"));
                    return Optional.of(branch);
                }    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateBranch(Branch branch) {
        String sql = "UPDATE branch SET name = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, branch.getName());
            ps.setString(2, branch.getCityId());
            ps.setInt(3, branch.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Branch> findAllBranch() {
        String sql = "SELECT * FROM branch";
        List<Branch> branch = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Branch branch1 = new Branch(rs.getInt("id"), rs.getString("name"), rs.getString("city_id"));
                    branch.add(branch1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }
    
}
