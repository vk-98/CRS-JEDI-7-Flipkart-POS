package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.sql.SQLException;

public interface StudentInterface {
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) throws StudentNotRegisteredException;

    String registerForSemester(String studentId);

    void viewGrades(int studentId) throws SQLException;

    boolean payFee(String studentId, String studentRegistrationId, double amount);
}
