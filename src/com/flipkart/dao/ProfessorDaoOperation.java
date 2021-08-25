package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public class ProfessorDaoOperation implements ProfessorDaoInterface{
    @Override
    public List<Course> getCoursesByProfessor(int userId) {
        return null;
    }

    @Override
    public List<Student> getEnrolledStudents(int profId) {
        return null;
    }

    @Override
    public int getProfessorById(int userId) {
        return 0;
    }

    @Override
    public Boolean addGrade(int studentId, int courseId, String grade) {
        return null;
    }
}
