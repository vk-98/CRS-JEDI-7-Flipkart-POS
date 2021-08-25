package com.flipkart.business;

public interface UserInterface {
    boolean validateUser(String emailId, String password);

    boolean updateUserPassword(String password);

    String getRole(String emailId);
}
