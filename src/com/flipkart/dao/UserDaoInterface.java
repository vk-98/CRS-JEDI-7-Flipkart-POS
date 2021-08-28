package com.flipkart.dao;

import com.flipkart.bean.User;

/**
 * @author JEDI-07
 * User Dao Interface
 */
public interface UserDaoInterface {
    /**
     * method for authenticating the user with database
     * @param emailId
     * @param password
     * @return isAuthenticated
     */
    public User authenticate(String emailId, String password);

    /**
     * method for updating user password
     * @param userId
     * @param newPassword
     * @return
     */
    public boolean updatePassword(int userId, String newPassword);

    /**
     * method for crearting a user
     * @param name
     * @param email
     * @param password
     * @param role
     * @param phoneNo
     * @return IsUserCreated
     */
    public boolean createUser(String name, String email, String password, String role, String phoneNo);

    public int getUserIdByEmail(String email);
}
