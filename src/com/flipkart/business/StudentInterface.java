package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.exceptions.StudentNotRegisteredException;

public interface StudentInterface {
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) throws StudentNotRegisteredException;

    String registerForSemester(String studentId);

    void viewGrades(String studentId, String semesterRegistrationId);

    boolean payFee(String studentId, String studentRegistrationId, double amount);
}
