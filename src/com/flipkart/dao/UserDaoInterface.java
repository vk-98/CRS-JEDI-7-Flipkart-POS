package com.flipkart.dao;

public interface UserDaoInterface {
    public boolean verifyCredentials(String emailId, String password);

    public boolean updatePassword(int userId, String newPassword);

    public String getRole(int userId);
}
