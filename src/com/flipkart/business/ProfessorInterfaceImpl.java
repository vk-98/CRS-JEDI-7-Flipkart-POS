package com.flipkart.business;

import com.flipkart.bean.Course;

public class ProfessorInterfaceImpl implements ProfessorInterface {
    @Override
    public boolean addGrad(String studentId, String courseId, String grade) {

        return false;
    }

    @Override
    public void viewEnrolledStudents(String professorId) {

    }

    @Override
    public void getCourses(String professorId) {

    }

    @Override
    public boolean selectCourse(String professorId, String courseId) {
        return false;
    }

    @Override
    public boolean deselectCourse(String courseId) {
        return false;
    }
}
