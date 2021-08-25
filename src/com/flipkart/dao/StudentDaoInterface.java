package com.flipkart.dao;

import com.flipkart.bean.Student;

public interface StudentDaoInterface {
    public boolean addStudent(Student student);

    public int getStudentById(int userId);

    public boolean isApproved(int studentId);

    public Student getStudent(int studentId);
}