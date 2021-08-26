package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.exceptions.StudentNotRegisteredException;

/**
 * Interface for Student Operations
 */
public interface StudentInterface {

    /**
     * Method to make a Student Register for CRS
     * @param studentName
     * @param studentEmailId
     * @param studentPassword
     * @param studentPhoneNo
     * @return Student : returns an Object of Student after successful registration
     * @throws StudentNotRegisteredException
     */
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) throws StudentNotRegisteredException;

    String registerForSemester(String studentId);

    /**
     * Method to display the Grades of the Students for the Semester
     * @param studentId
     * @param semesterRegistrationId
     */
    void viewGrades(String studentId, String semesterRegistrationId);

    /**
     * Method for making the Student to pay Fee for the Semester
     * @param studentId
     * @param studentRegistrationId
     * @param amount
     */
    boolean payFee(String studentId, String studentRegistrationId, double amount);
}
