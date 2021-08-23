package com.flipkart.business;

public interface StudentRegistrationInterface {
    boolean addCourse(String studentId, String courseId);
    boolean dropCourse(String studentId, String courseId);
    void viewCourses(String studentId);
    void viewRegisteredCourses(String studentId);
    double calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);
    void setRegistrationStatus(String studentId, boolean status);
}
