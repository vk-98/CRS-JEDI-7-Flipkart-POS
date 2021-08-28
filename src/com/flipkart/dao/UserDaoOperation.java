package com.flipkart.dao;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JEDI-07
 * Implementation of User Dao Interface
 */
public class UserDaoOperation implements UserDaoInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    static Connection conn = DBUtil.getConnection();

    /**
     * method for authenticating the user with database
     *
     * @param emailId  emailId of User
     * @param password Password of the user
     * @return Returns Authenticated User Object
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
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * method for updating user password
     *
     * @param userId      Unique Id of the User
     * @param newPassword Password to be set
     * @return returns true if password is updated successfully
     */
    @Override
    public boolean updatePassword(int userId, String newPassword) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.UPDATE_PASSWORD);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }


    /**
     * method for creating a user
     *
     * @param name     name of the User
     * @param email    Email Id of the User
     * @param password Password of the User
     * @param role     Role of the User
     * @param phoneNo  PhoneNo of the User
     * @return returns true if the user is created successfully
     */
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
            return rowAffected == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to get UserId by EmailId
     *
     * @param email EmailId of the User
     * @return returns UserId
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
            logger.info("Error: " + e.getMessage());
        }
        return -1;
    }
}