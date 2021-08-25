package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;

import java.util.HashMap;

public class StudentInterfaceImpl implements StudentInterface {
    @Override
    public Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) {

        StudentDaoInterface studentDao = new StudentDaoOperation();

        Student student = new Student(studentName, studentEmailId, studentPassword, studentPhoneNo);
        boolean added = studentDao.addStudent(student);
        return added ? student : null;
    }

    @Override
    public String registerForSemester(String studentId) {
        return null;
    }

    @Override
    public void viewGrades(String studentId, String semesterRegistrationId) {

    }

    @Override
    public boolean payFee(String studentId, String studentRegistrationId, double amount) {
        return false;
    }
}