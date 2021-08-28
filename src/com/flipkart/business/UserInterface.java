package com.flipkart.business;

/**
 * @author JEDI-07
 * User Interface
 */
public interface UserInterface {
    /**
     * method for validating user with email and password
     *
     * @param emailId  emailId of the User
     * @param password password for the User
     * @return returns true if validation is successful
     */
    boolean validateUser(String emailId, String password);

    /**
     * method for updating user password
     *
     * @param newPassword New Password of the user
     * @return returns true if Password is Updated
     */
    boolean updateUserPassword(String newPassword);

    /**
     * method for logging out the user.
     *
     * @return returns true if user logged out successfully
     */
    boolean logout();

}
