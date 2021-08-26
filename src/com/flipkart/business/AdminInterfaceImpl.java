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

/**
 * Implementations of Admin Operations
 */
public class AdminInterfaceImpl implements AdminInterface {
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
    StudentDaoInterface studentDaoInterface = new StudentDaoOperation();

    /**
     * Method to add Course into the Course Catalog
     * @param courseName
     * @param courseDescription
     * @@param courseFee
     */
    @Override
    public void addCourse(String courseName, String courseDescription, double courseFee) {
        Course course = new Course(courseName, courseDescription, courseFee);
        adminDaoInterface.addCourse(course);
        System.out.println("Course Added Successfully");
    }

    /**
     * Method to remove Course from Course Catalog
     * @param courseId
     */
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

    /**
     *  Method to approve Student's Registration
     * 	@param studentId
     */
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

    /**
     *  Method to add Professor into the DataBase
     * 	@param name
     * 	@param emailId
     * 	@param password
     * 	@param phoneNo
     * 	@param department
     * 	@param designation
     */
    @Override
    public void addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation) {
        boolean added = adminDaoInterface.addProfessor(name, emailId, password, phoneNo, department, designation);
        if (added) System.out.println("Professor created successfully.");
        else System.out.println("Professor not added.");
    }

    /**
     * Method to view all the pending Registration/Admission requests by Students
     */
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

    /**
     * Method to view all the Professors in the DataBase
     */
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