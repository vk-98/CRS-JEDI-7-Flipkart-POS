package com.flipkart.business;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.util.List;

public interface StudentInterface {
    Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo);

    String registerForSemester(String studentId);

    List<Grade> getGrades();

    void getStudent();
}
