package com.flipkart.dao;

import com.flipkart.bean.User;

/**
 * @author JEDI-07
 * User Dao Interface
 */
public interface UserDaoInterface {
    /**
     * method for authenticating the user with database
     *
     * @param emailId  emailId of User
     * @param password Password of the user
     * @return Returns Authenticated User Object
     */
    public User authenticate(String emailId, String password);

    /**
     * method for updating user password
     *
     * @param userId      Unique Id of the User
     * @param newPassword Password to be set
     * @return returns true if password is updated successfully
     */
    public boolean updatePassword(int userId, String newPassword);

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
    public boolean createUser(String name, String email, String password, String role, String phoneNo);

    /**
     * Method to get UserId by EmailId
     *
     * @param email EmailId of the User
     * @return returns UserId
     */
    public int getUserIdByEmail(String email);
}
