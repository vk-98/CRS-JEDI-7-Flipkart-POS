package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Roles;
import com.flipkart.dao.UserDaoOperation;

public class UserInterfaceImpl implements UserInterface {

    public static User user = null;
    UserDaoOperation userDaoOperation = new UserDaoOperation();

    @Override
    public boolean validateUser(String emailId, String password) {
        user = userDaoOperation.authenticate(emailId, password);
        return (user != null);
    }


    @Override
    public String getRole(String emailId) {
        if (user != null) {
            return user.getRole();
        }
        System.out.println("User is not loggedIn");
        return null;
    }

    @Override
    public boolean updateUserPassword(String newPassword) {
        userDaoOperation.updatePassword(user.getId(), newPassword);
        System.out.println("User Password Updated Successfully");
        return true;
    }

    public static void logout() {
        user = null;
        System.out.println("You are successfully logged out.");
    }
}
