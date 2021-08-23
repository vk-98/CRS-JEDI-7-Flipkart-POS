package com.flipkart.bean;

public class Grades {
    private String registerdCourseId;
    private String studentId;

    public Grades() {
    }

    public String getRegisterdCourseId() {
        return registerdCourseId;
    }

    public Grades(String registerdCourseId, String studentId, double gpa) {
        this.registerdCourseId = registerdCourseId;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public void setRegisterdCourseId(String registerdCourseId) {
        this.registerdCourseId = registerdCourseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    private double gpa;

}
