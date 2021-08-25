package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDaoInterface {
    public List<Course> getCoursesByProfessor(int userId);

    public List<Student> getEnrolledStudents(int profId);

    public int getProfessorById(int userId);

    public Boolean addGrade(int studentId, int courseId, String grade);
}
