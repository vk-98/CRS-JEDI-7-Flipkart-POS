package com.flipkart.bean;

/**
 * @author JEDI-07
 * Grade Bean
 */
public class Grade {
    private int courseId;
    private String courseName;
    private int studentId;
    private double gpa;

    public Grade() {
    }

    /**
     * Parameterized Constructor
     *
     * @param courseId  Unique Id of the Course
     * @param studentId Unique Id of the Student
     * @param gpa       GPA of the Student for a Course
     */
    public Grade(int courseId, int studentId, double gpa) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    /**
     * Getter for Course Name
     *
     * @return CourseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Setter for Course Name
     *
     * @param courseName Name of the Course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Getter for CourseId
     *
     * @return Course Id
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Setter for CourseId
     *
     * @param courseId Unique Id of the Course
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter for Student Id
     *
     * @return Unique Id of the Student
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Setter for Student ID
     *
     * @param studentId Unique Id of the Student
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter for GPA
     *
     * @return GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Setter for GPA
     *
     * @param gpa Grade for a Student
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
