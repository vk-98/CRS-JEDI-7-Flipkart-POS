package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

//import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementations for User Dao Operations
 */
public class UserDaoOperation implements UserDaoInterface {
    static Connection conn = DBUtil.getConnection();

    /**
     * Method to verify credentials of Users from DataBase
     * @param emailId
     * @param password
     * @return Verify credentials operation status
     */
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

    /**
     * Method to update password of user in DataBase
     * @param userId
     * @param newPassword
     * @return Update Password operation Status
     */
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

    /**
     * Method to get Role of User from DataBase
     * @param userId
     * @return Role
     */
    @Override
    public String getRole(int userId) {
        return null;
    }

    /**
     * Method to create a new User
     * @param name
     * @param email
     * @param password
     * @param role
     * @param phoneNo
     */
    @Override
    public boolean createUser(String name, String email, String password, String role, String phoneNo) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_USER_QUERY);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phoneNo);
            ps.setString(5, role);

            int rowAffected = ps.executeUpdate();

            System.out.println("rowa:" + rowAffected);
            return rowAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to get User Id by his Email
     * @param email
     * @return User Id
     */
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