package com.flipkart.bean;

public class Course {
    private String courseId;
    private String professorId;
    private String courseName;
    private double courseFee;
    private String courseDescription;
    private int studentCount;
    private int studentId;

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Course(String courseId, String professorId, String courseName, double courseFee, int studentCount, String courseDescription, int studentId) {
        this.courseId = courseId;
        this.professorId = professorId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.studentCount = studentCount;
        this.courseDescription = courseDescription;
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
