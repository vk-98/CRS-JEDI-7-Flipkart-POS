package com.flipkart.bean;

/**
 * Course Class
 */
public class Course {
    private int courseId;
    private int professorId;
    private String courseName;
    private String courseDescription;
    private double courseFee;
    private int studentCount;

    /**
     * Method to get Description of the course
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * Method to set Description of the course
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Course() {
    }

    public Course(String courseName, String courseDescription, double courseFee) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseFee = courseFee;
        this.studentCount = 0;
    }

    public Course(int professorId, String courseName, String courseDescription, double courseFee) {
        this.professorId = professorId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseFee = courseFee;
        this.studentCount = 0;
    }


    /**
     * Method to get CourseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Method to set CourseId
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Method to get ProfessorId
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Method to set ProfessorId
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Method to get Course Name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set Course Name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get Course Fee
     */
    public double getCourseFee() {
        return courseFee;
    }

    /**
     * Method to set Course Name
     */
    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    /**
     * This Method returns the total number of students registered for the course
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * This Method sets the total number of students registered for the course
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
