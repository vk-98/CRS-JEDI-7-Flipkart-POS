package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.ProfessorNotAddedException;
import com.flipkart.exceptions.StudentAlreadyApprovedException;
import com.flipkart.exceptions.StudentNotFoundException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class AdminInterfaceImpl implements AdminInterface {
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
    StudentDaoInterface studentDaoInterface = new StudentDaoOperation();

    @Override
    public void addCourse(String courseName, String courseDescription, double courseFee) {
        Course course = new Course(courseName, courseDescription, courseFee);
        adminDaoInterface.addCourse(course);
        System.out.println("Course Added Successfully");
    }

    @Override
    public void removeCourse(int courseId) {
        try {
            int rowsAffected = adminDaoInterface.removeCourse(courseId);
            if (rowsAffected == 0) {
                throw new CourseNotFoundException(courseId);
            }
            System.out.println("Course removed successfully");
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void approveStudentRequest(int studentId) {

        try {
            Student st = studentDaoInterface.getStudentByStudentId(studentId);
            if (st == null) {
                throw new StudentNotFoundException(studentId);
            }
            if (st.isApproved()) {
                throw new StudentAlreadyApprovedException(studentId);
            }
            boolean isApproved = adminDaoInterface.approveStudent(studentId);
            if (isApproved) {
                System.out.println("Student Addmission Request approved");
            }
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (StudentAlreadyApprovedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation) {
        boolean added = adminDaoInterface.addProfessor(name, emailId, password, phoneNo, department, designation);
        if (added) System.out.println("Professor created successfully.");
        else System.out.println("Professor not added.");
    }

    @Override
    public void listAdmissionRequests() {
        List<Student> requests = adminDaoInterface.viewPendingAdmissions();
        if (requests == null || requests.size() == 0) {
            System.out.println("No pending addmission requests");
        } else {
            System.out.println("Student ID | Student Name | Student Email ID");
            for (Student st : requests) {
                System.out.println(st.getId() + " | " + st.getUserName() + " | " + st.getUserEmailId());
            }
        }
    }

    @Override
    public void viewProfessors() {
        List<Professor> professors = adminDaoInterface.viewProfessors();
        if (professors == null || professors.size() == 0) {
            System.out.println("No Professors registered");
        } else {
            System.out.println("Prof ID | Prof Name | Prof Email ID | Prof Department | Prof Designation");
            for (Professor p : professors) {
                System.out.println(
                        p.getProfessorId()
                                + " | "
                                + p.getUserName()
                                + " | "
                                + p.getUserEmailId()
                                + " | "
                                + p.getDepartment()
                                + " | "
                                + p.getDesignation()
                );
            }
        }
    }
}