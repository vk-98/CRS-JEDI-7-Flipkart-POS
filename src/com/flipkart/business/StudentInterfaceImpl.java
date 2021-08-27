package com.flipkart.business;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;
import org.apache.log4j.Logger;

public class StudentInterfaceImpl implements StudentInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    public static Student student = null;
    StudentDaoInterface studentDaoInterface = new StudentDaoOperation();

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
            logger.info(ex.getStudentName() + " is not registered");
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
    public void setStudent() {
        student = studentDaoInterface.getStudentByEmailId(UserInterfaceImpl.user.getUserEmailId());
    }

    @Override
    public boolean payFee(String studentId, String studentRegistrationId, double amount) {
        return false;
    }
}