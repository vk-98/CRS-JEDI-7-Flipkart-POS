package com.flipkart.business;

public interface ProfessorInterface {
    void addGrade(int studentId, int courseId, double grade);

    void viewEnrolledStudents(int courseId);

    void viewSelectedCourses();

    void selectCourse(int courseId);

    void deselectCourse(int courseId);

    void viewAvailableCourses();

    void getProfessor();

}
