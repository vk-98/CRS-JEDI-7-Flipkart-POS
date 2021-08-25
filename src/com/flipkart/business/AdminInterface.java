package com.flipkart.business;

import com.flipkart.bean.Professor;

public interface AdminInterface {
    boolean addCourse(String courseId, String courseName, int studentCount, double courseFee);
    boolean removeCourse(String courseId);
    boolean approveStudentRequest(String studentId);
    boolean addProfessor(Professor professor);
    void generateResult();

}
