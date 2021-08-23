package com.flipkart.business;

public interface StudentInterface {
    String registerForSemester(String studentId);
    void viewGrades(String studentId, String semesterRegestrationId);
    boolean payFee(String studentId, String studentRegistrationId, double amount);


}
