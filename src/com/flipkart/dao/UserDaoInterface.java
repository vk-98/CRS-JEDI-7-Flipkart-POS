package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDaoInterface {
    public User authenticate(String emailId, String password);

    public boolean updatePassword(int userId, String newPassword);

    public String getRole(int userId);

    public boolean createUser(String name, String email, String password, String role, String phoneNo);

    public int getUserIdByEmail(String email);
}
