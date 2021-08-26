package com.flipkart.bean;

/**
 * Grades Class
 */
public class Grades {
    private String registerdCourseId;
    private String studentId;

    public Grades() {
    }

    /**
     * Method to retrieve the registered CourseId of the corresponding Grade
     */
    public String getRegisterdCourseId() {
        return registerdCourseId;
    }

    public Grades(String registerdCourseId, String studentId, double gpa) {
        this.registerdCourseId = registerdCourseId;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    /**
     * Method to set the registered CourseId of the corresponding Grade
     */
    public void setRegisterdCourseId(String registerdCourseId) {
        this.registerdCourseId = registerdCourseId;
    }

    /**
     * Method to retrieve the studentId for whom the grade is assigned
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Method to set the studentId for whom the grade is assigned
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to retrieve the Grade
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Method to set the Grade
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    private double gpa;

}
