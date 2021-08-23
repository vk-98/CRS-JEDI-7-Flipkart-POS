package com.flipkart.business;

public interface AdminInterface {
    boolean addCourse(String courseId, String courseName, int studentCount, String courseFee);
    boolean removeCourse(String courseId);
    boolean approveStudentRequest(String studentId);
    boolean addProfessor(String professorId, String department, String designation);
    void generateResult();

}
