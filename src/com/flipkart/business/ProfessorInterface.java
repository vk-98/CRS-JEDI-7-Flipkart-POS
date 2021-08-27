package com.flipkart.business;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;

public interface ProfessorInterface {
    boolean addGrad(String studentId, String courseId, String grade) throws GradeNotAddedException;

    void viewEnrolledStudents(String professorId);

    void getCourses(String professorId);

    boolean selectCourse(String professorId, String courseId) throws CourseNotFoundException;

    boolean deselectCourse(String professorId, String courseId) throws CourseNotFoundException;

    String getProfessorIdByEmail(String emailId);
}
