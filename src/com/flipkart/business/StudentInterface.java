package com.flipkart.business;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.util.List;

/**
 * @author JEDI-07
 * Student Interface
 */
public interface StudentInterface {
    /**
     * method for registering a student
     *
     * @param studentName
     * @param studentEmailId
     * @param studentPassword
     * @param studentPhoneNo
     * @return
     */
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo);

    /**
     * method getting all the grades
     *
     * @return list of grades
     */
    List<Grade> getGrades();

    /**
     * method for getting student by emailid
     */
    void getStudentByEmailId();
}
