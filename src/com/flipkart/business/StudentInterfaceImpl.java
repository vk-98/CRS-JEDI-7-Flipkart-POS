package com.flipkart.business;

import com.flipkart.bean.Student;

import java.util.HashMap;

public class StudentInterfaceImpl implements StudentInterface {
    static HashMap<String, Student> registeredStudents = new HashMap<String, Student>();

    @Override
    public Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo, String studentBranch) {
        boolean isPresent = registeredStudents.containsKey(studentEmailId);

        if (isPresent) {
            System.out.println("Student with emailId " + studentEmailId + " already exists");
            return null;
        }
        Student student = new Student(studentName, studentEmailId, studentPassword, studentPhoneNo, studentBranch);
        registeredStudents.put(studentEmailId, student);
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
