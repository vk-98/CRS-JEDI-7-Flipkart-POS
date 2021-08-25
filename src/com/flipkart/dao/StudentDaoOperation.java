package com.flipkart.dao;

import com.flipkart.bean.Student;

public class StudentDaoOperation implements StudentDaoInterface {
    @Override
    public int addStudent(Student student) {
        UserDaoInterface userDaoInterface= new UserDaoOperation();
        boolean isSuccess= userDaoInterface.createUser(student.getUserName(),student.getUserEmailId(),student.getUserPassword(),student.getRole(),student.getPhoneNo());
        if(isSuccess)
        {

        }
        return 0;
    }

    @Override
    public int getStudentById(int userId) {
        return 0;
    }

    @Override
    public boolean isApproved(int studentId) {
        return false;
    }
}
