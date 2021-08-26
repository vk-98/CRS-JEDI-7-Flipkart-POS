package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;

/**
 * Implementations for Student Operations
 */
public class StudentInterfaceImpl implements StudentInterface {

    /**
     * Method to make a Student Register for CRS
     * @param studentName
     * @param studentEmailId
     * @param studentPassword
     * @param studentPhoneNo
     * @return Student : returns an Object of Student after successful registration
     * @throws StudentNotRegisteredException
     */
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

    /**
     * Method to display the Grades of the Students for the Semester
     * @param studentId
     * @param semesterRegistrationId
     */
    @Override
    public void viewGrades(String studentId, String semesterRegistrationId) {

    }

    /**
     * Method for making the Student to pay Fee for the Semester
     * @param studentId
     * @param studentRegistrationId
     * @param amount
     */
    @Override
    public boolean payFee(String studentId, String studentRegistrationId, double amount) {
        return false;
    }
}