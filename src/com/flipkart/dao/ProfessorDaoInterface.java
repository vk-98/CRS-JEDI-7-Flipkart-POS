package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDaoInterface {
    public Professor getProfessorByUserId(int userId);

    public List<Course> getCoursesByProfessorId();

    public List<Student> getEnrolledStudents(int courseId);

    public Boolean addGrade(int studentId, int courseId, double grade);

    public boolean IsStudentAlreadyGraded(int studentId, int courseId);

    public List<Course> viewAvailableCourses();

    public boolean IsCourseAvailable(int courseId);

    public boolean selectCourse(int courseId);

    public boolean IsCourseSelected(int courseId);

    public boolean deselectCourse(int courseId);

    public boolean IsStudentEnrolled(int studentId, int courseId);
}
