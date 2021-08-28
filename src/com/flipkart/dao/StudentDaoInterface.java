package com.flipkart.dao;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentDaoInterface {
    public boolean addStudent(String studentName, String studentEmailId, String studentPassword, String studentPhoneNot);
    public Student getStudentByEmailId(String emailId);
    public Student getStudentByStudentId(int studentId);

    public List<Grade> getGrades(int studentId);
}