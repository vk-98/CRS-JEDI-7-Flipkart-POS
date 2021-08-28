package com.flipkart.dao;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Student Dao Interface
 */
public interface StudentDaoInterface {
    /**
     * Method to add Student to Database
     *
     * @param studentName     Name of the Student
     * @param studentEmailId  EmailId of the Student
     * @param studentPassword Password for the Student
     * @param studentPhoneNo  Phone Number of the Student
     * @return returns true if student is added successfully to the database
     */
    boolean addStudent(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo);

    /**
     * Method for getting student by Email Id
     *
     * @param emailId Email Id of the Student
     * @return Returns Student Object
     */
    Student getStudentByEmailId(String emailId);

    /**
     * Method for getting student by student Id
     *
     * @param studentId Unique Id of the Student
     * @return Returns Student Object
     */
    Student getStudentByStudentId(int studentId);

    /**
     * Method to get Grades of a Student
     *
     * @param studentId Unique Id of the Student
     * @return List of Grades
     */
    List<Grade> getGrades(int studentId);
}