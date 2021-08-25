package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public class UserInterfaceImpl implements UserInterface {

    static User user = null;

    @Override
    public boolean validateUser(String emailId, String password) {
        //Also to Write validation for Professor As well
        if (!StudentInterfaceImpl.registeredStudents.containsKey(emailId)) {
            System.out.println("Incorrect Email Id, Try Again");
            return false;
        }
        if (!StudentInterfaceImpl.registeredStudents.get(emailId).getUserPassword().equals(password)) {
            System.out.println("Incorrect Password, Try Again");
            return false;
        }
        user = StudentInterfaceImpl.registeredStudents.get(emailId);
        return true;
    }


    @Override
    public String getRole(String emailId) {
        return StudentInterfaceImpl.registeredStudents.get(emailId).getRole();
    }

    @Override
    public boolean updateUserPassword(String emailId, String password) {
        if (!StudentInterfaceImpl.registeredStudents.containsKey(emailId)) {
            System.out.println("Incorrect Email Id");
            return false;
        }
        Student st = StudentInterfaceImpl.registeredStudents.get(emailId);
        st.setUserPassword(password);
        StudentInterfaceImpl.registeredStudents.put(emailId, st);
        return true;
    }

    public static void logout() {
        user = null;
        System.out.println("You are successfully logged out.");
    }
}
