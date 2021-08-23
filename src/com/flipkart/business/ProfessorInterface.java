package com.flipkart.business;

public interface ProfessorInterface {
    boolean addGrad(String studentId, String courseId, String grade);
    void viewEnrolledStudents(String professorId);
    void getCourses(String professorId);
    boolean selectCourse(String professorId, String courseId);
    boolean deselectCourse(String courseId);

}
