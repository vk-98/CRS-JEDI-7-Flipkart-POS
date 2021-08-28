package com.flipkart.business;

/**
 * @author JEDI-07
 * User Interface
 */
public interface UserInterface {
    /**
     * method for validating user with email and password
     *
     * @param emailId
     * @param password
     * @return isAutheniticated
     */
    boolean validateUser(String emailId, String password);

    /**
     * method for updating user password
     *
     * @param newPassword
     * @return isPasswordUpdated
     */
    boolean updateUserPassword(String newPassword);

    /**
     * method for logging out the user.
     *
     * @return isUserLoggedOut
     */
    boolean logout();

}
