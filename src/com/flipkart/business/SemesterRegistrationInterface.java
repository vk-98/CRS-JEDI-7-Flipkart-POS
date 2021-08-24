package com.flipkart.business;

public interface SemesterRegistrationInterface {

    boolean addPrimaryCourse(String studentId, String courseId);
    boolean addSecondaryCourse(String studentId, String courseId);
    boolean dropCourse(String studentId, String courseId);
    void viewRegisteredCourses(String studentId);
    double calculateFee(String studentId);
    boolean submitCourseChoices(String studentId);
    boolean getRegistrationStatus(String studentId);
    void setRegistrationStatus(String studentId, boolean status);
}
