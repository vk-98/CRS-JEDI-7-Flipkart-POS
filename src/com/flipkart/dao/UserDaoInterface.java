package com.flipkart.dao;

import com.flipkart.bean.User;

/**
 * Interface for User Dao Operations
 */
public interface UserDaoInterface {

    /**
     * Method to verify credentials of Users from DataBase
     * @param emailId
     * @param password
     * @return Verify credentials operation status
     */
    public User authenticate(String emailId, String password);

    /**
     * Method to update password of user in DataBase
     * @param userId
     * @param newPassword
     * @return Update Password operation Status
     */
    public boolean updatePassword(int userId, String newPassword);

    /**
     * Method to get Role of User from DataBase
     * @param userId
     * @return Role
     */
    public String getRole(int userId);

    /**
     * Method to create a new User
     * @param name
     * @param email
     * @param password
     * @param role
     * @param phoneNo
     */
    public boolean createUser(String name, String email, String password, String role, String phoneNo);

    /**
     * Method to get User Id by his Email
     * @param email
     * @return User Id
     */
    public int getUserIdByEmail(String email);
}
