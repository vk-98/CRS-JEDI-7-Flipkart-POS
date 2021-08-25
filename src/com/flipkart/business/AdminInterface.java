package com.flipkart.business;

import com.flipkart.bean.Professor;

public interface AdminInterface {
    void addCourse(String courseName, String courseDescription, double courseFee);

    void removeCourse(int courseId);

    void approveStudentRequest(int studentId);

    void addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    void listAdmissionRequests();

    void viewProfessors();

}
