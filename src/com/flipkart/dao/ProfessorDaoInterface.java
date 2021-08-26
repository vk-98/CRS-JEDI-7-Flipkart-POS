package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDaoInterface {
    public List<Course> getCoursesByProfessor(int userId);

    public List<Course> getCoursesByProfessorId(int professorId);

    public List<Student> getEnrolledStudents(int profId);

    public int getProfessorById(int userId);

    public Boolean addGrade(int studentId, int courseId, String grade);

    public boolean selectCourse(int professorId, int courseId);

    public boolean deselectCourse(int professorId, int courseId);

    String getProfessorIdByEmailId(String emailId);

}
