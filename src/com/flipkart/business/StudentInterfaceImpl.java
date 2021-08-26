package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;

public class StudentInterfaceImpl implements StudentInterface {
    @Override
    public Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) throws StudentNotRegisteredException {
        Student student = null;

        try {
            StudentDaoInterface studentDao = new StudentDaoOperation();

            student = new Student(studentName, studentEmailId, studentPassword, studentPhoneNo);
            boolean added = studentDao.addStudent(student);
            if (!added) {
                student = null;
                throw new StudentNotRegisteredException(studentName);
            }
        } catch (StudentNotRegisteredException ex) {
            System.out.println(ex.getStudentName() + " is not registered");
        }
        return student;
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