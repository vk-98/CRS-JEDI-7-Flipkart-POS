package com.flipkart.business;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Roles;
import com.flipkart.dao.UserDaoOperation;
import org.apache.log4j.Logger;

public class UserOperation implements UserInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
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
        logger.info("User is not loggedIn");
        return null;
    }

    @Override
    public boolean updateUserPassword(String newPassword) {
        userDaoOperation.updatePassword(user.getId(), newPassword);
        logger.info("User Password Updated Successfully");
        return true;
    }

    public static void logout() {
        user = null;
        logger.info("You are successfully logged out.");
    }
}
