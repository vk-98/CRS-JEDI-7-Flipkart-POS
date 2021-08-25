package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

public interface AdminDaoInterface {
    public void addCourse(Course course);

    public int removeCourse(int courseId);

    public List<Student> viewPendingAdmissions();

    public boolean approveStudent(int studentId);

    public boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    public List<Course> viewCourses();

    public List<Professor> viewProfessors();
}
