package com.flipkart.dao;

import com.flipkart.bean.Student;

/**
 * Interface for Student Dao Operations
 */
public interface StudentDaoInterface {

    /**
     * Method to add student to database
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     */
    public boolean addStudent(Student student);

    /**
     * Method to retrieve Student Id from User Id
     * @param userId
     * @return Student Id
     */
    public int getStudentById(int userId);

    /**
     * Method to check if Student is approved
     * @param studentId
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(int studentId);

    /**
     * Method to retrieve Student from Student Id
     * @param studentId
     * @return Student : Object of Student
     */
    public Student getStudentByStudentId(int studentId);
}