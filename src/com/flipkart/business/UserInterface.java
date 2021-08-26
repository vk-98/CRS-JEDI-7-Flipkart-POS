package com.flipkart.business;

/**
 * Interface for User Operations
 */
public interface UserInterface {

    /**
     * Method to verify the credentials of a User
     * @param emailId
     * @param password
     */
    boolean validateUser(String emailId, String password);

    /**
     * Method to update the Password of a User
     * @param password
     */
    boolean updateUserPassword(String password);

    /**
     * Method to get the Role of the User
     * @param emailId
     * @return Role of the User
     */
    String getRole(String emailId);
}
