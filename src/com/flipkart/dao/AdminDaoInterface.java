package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

/**
 * Interface for Admin Dao Operations
 */
public interface AdminDaoInterface {

    /**
     * Add Course using SQL commands
     * @param course
     */
    public void addCourse(Course course);

    /**
     * Delete Course using SQL commands
     * @param courseId
     */
    public int removeCourse(int courseId);

    /**
     * Fetch Students yet to approved using SQL commands
     * @return List of Students yet to approved
     */
    public List<Student> viewPendingAdmissions();

    /**
     * Approve Student using SQL commands
     * @param studentId
     */
    public boolean approveStudent(int studentId);

    /**
     * Add professor using SQL commands
     * @param name
     * @param emailId
     * @param password
     * @param phoneNo
     * @param department
     * @param designation
     */
    public boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    /**
     * View courses in the catalog
     * @return List of courses in the catalog
     */
    public List<Course> viewCourses();

    /**
     * View professors who enrolled in the Semester
     * @return List of the professors in the institute
     */
    public List<Professor> viewProfessors();
}
