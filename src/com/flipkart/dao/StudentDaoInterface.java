package com.flipkart.dao;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Student Dao Interface
 */
public interface StudentDaoInterface {

     boolean addStudent(String studentName, String studentEmailId, String studentPassword, String studentPhoneNot);
     Student getStudentByEmailId(String emailId);

    /**
     * method for getiing student by student Id
     * @param studentId
     * @return
     */
     Student getStudentByStudentId(int studentId);

     List<Grade> getGrades(int studentId);
}