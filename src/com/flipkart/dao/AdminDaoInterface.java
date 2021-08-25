package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

public interface AdminDaoInterface {
    public void addCourse(Course course);

    public List<Student> viewPendingAdmissions();

    public void approveStudent(int studentId);

    public void addProfessor(Professor professor);

    public void addUser(User user);

    public void assignCourse(String courseCode, String professorId);

    public List<Course> viewCourses();

    public List<Professor> viewProfessors();
}
