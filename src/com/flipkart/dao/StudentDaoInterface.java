package com.flipkart.dao;

import com.flipkart.bean.Student;

public interface StudentDaoInterface {
    public int addStudent(Student student);

    public int getStudentById(int userId);

    public boolean isApproved(int studentId);
}
