package com.flipkart.business;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Roles;
import com.flipkart.dao.UserDaoOperation;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Implementation of User Interface
 */
public class UserOperation implements UserInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    public static User user = null;
    UserDaoOperation userDaoOperation = new UserDaoOperation();

    /**
     * method for validating user with email and password
     *
     * @param emailId  emailId of the User
     * @param password password for the User
     * @return returns true if validation is successful
     */
    @Override
    public boolean validateUser(String emailId, String password) {
        user = userDaoOperation.authenticate(emailId, password);
        return (user != null);
    }

    /**
     * method for updating user password
     *
     * @param newPassword New Password of the user
     * @return returns true if Password is Updated
     */
    @Override
    public boolean updateUserPassword(String newPassword) {
        return userDaoOperation.updatePassword(user.getId(), newPassword);
    }

    /**
     * method for logging out the user.
     *
     * @return returns true if user logged out successfully
     */
    public boolean logout() {
        user = null;
        return true;
    }
}
