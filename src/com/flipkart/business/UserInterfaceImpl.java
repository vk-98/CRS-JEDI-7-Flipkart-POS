package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Roles;
import com.flipkart.dao.UserDaoOperation;

public class UserInterfaceImpl implements UserInterface {

    public static User user = null;

    @Override
    public boolean validateUser(String emailId, String password) {
        UserDaoOperation userDaoOperation = new UserDaoOperation();
        user = userDaoOperation.authenticate(emailId, password);
        return (user != null);
    }


    @Override
    public String getRole(String emailId) {
        if (emailId.equals("admin"))
            return AdminInterfaceImpl.admin.getRole();
        // return StudentInterfaceImpl.registeredStudents.get(emailId).getRole();
        return Roles.Student;
    }

    @Override
    public boolean updateUserPassword(String newPassword) {

        return true;
    }

    public static void logout() {
        user = null;
        System.out.println("You are successfully logged out.");
    }
}
