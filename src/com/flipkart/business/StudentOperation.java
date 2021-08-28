package com.flipkart.business;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;
import com.flipkart.exceptions.StudentNotRegisteredException;
import org.apache.log4j.Logger;

import java.util.List;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);
    public static Student student = null;
    StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface = new SemesterRegistrationDaoOperation();
    SemesterRegistrationInterface semesterRegistrationInterface = new SemesterRegistrationOperation();

    @Override
    public Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) {
        Student student = null;
        try {
            StudentDaoInterface studentDao = new StudentDaoOperation();
            boolean added = studentDao.addStudent(studentName, studentEmailId, studentPassword, studentPhoneNo);
            if (!added) {
                throw new StudentNotRegisteredException(studentName);
            } else {
                student = new Student(studentName, studentEmailId, studentPassword, studentPhoneNo);
            }
        } catch (StudentNotRegisteredException ex) {
            logger.info(logger.getClass());
            logger.error(ex.getStudentName() + " is not registered.");
        }
        return student;
    }

    @Override
    public String registerForSemester(String studentId) {
        return null;
    }

    @Override
    public List<Grade> getGrades() {
        boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();
        if(!isRegistered) {
            logger.info("Student is not registered for the semester");
            return null;
        }

        boolean paymentStatus = semesterRegistrationDaoInterface.getPaymentStatus();
        if(paymentStatus) {
            System.out.println("yaha1");
            return studentDaoInterface.getGrades(StudentOperation.student.getStudentId());
        }
        return null;
    }

    @Override
    public void getStudent() {
        student = studentDaoInterface.getStudentByEmailId(UserOperation.user.getUserEmailId());
    }
}