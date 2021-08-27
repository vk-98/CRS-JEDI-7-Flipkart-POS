package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoOperation implements UserDaoInterface {
    static Connection conn = DBUtil.getConnection();

    @Override
    public User authenticate(String emailId, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_USER_EMAIL_PASSWORD);
            ps.setString(1, emailId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("phone")
                );
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.UPDATE_PASSWORD);
            ps.setString(1,newPassword);
            ps.setInt(2, userId);
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String getRole(int userId) {
        return null;
    }

    @Override
    public boolean createUser(String name, String email, String password, String role, String phoneNo) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_USER_QUERY);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setString(5, phoneNo);

            int rowAffected = ps.executeUpdate();

            System.out.println("rowa:" + rowAffected);
            return rowAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public int getUserIdByEmail(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_USER_ID);
            ps.setString(1, email);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return result.getInt("id");
            }

            return -1;

        } catch (SQLException e) {

        }
        return -1;
    }
}