package com.flipkart.business;

import java.sql.SQLException;

public interface SemesterRegistrationInterface {

    boolean addPrimaryCourse(int studentId, int courseId);

    boolean addSecondaryCourse(int studentId, int courseId);

    boolean dropCourse(int studentId, int courseId);

    void viewRegisteredCourses(int studentId);

    double calculateFee(int studentId) throws SQLException;

    boolean submitCourseChoices(int studentId);

    boolean getRegistrationStatus(int studentId);

    void setRegistrationStatus(int studentId, boolean status);
}
