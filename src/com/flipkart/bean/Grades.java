package com.flipkart.bean;

public class Grades {
    private int registerdCourseId;
    private int studentId;

    public Grades() {
    }

    public int getRegisterdCourseId() {
        return registerdCourseId;
    }

    public Grades(int registerdCourseId,int studentId, double gpa) {
        this.registerdCourseId = registerdCourseId;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public void setRegisterdCourseId(int registerdCourseId) {
        this.registerdCourseId = registerdCourseId;
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

    private double gpa;

}
