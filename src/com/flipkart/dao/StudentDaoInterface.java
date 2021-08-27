package com.flipkart.dao;

import com.flipkart.bean.Grades;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentDaoInterface {
    public boolean addStudent(Student student);

    public int getStudentById(int userId);

    public boolean isApproved(int studentId);

    public Student getStudentByEmailId(String emailId);
    public Student getStudentByStudentId(int studentId);

    public List<Grades> getGrades(int studentId);
}