package com.flipkart.bean;

public class Grade {
    private int courseId;
    private String courseName;
    private int studentId;
    private double gpa;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }



    public Grade() {
    }

    public int getCourseId() {
        return courseId;
    }

    public Grade(int courseId, int studentId, double gpa) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }



}
