package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo, String studentBranch)
    String registerForSemester(String studentId);
    void viewGrades(String studentId, String semesterRegistrationId);
    boolean payFee(String studentId, String studentRegistrationId, double amount);
}
