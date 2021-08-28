package com.flipkart.bean;

/**
 * @author JEDI-07
 * Course Bean
 */
public class Course {
    private int courseId;
    private int professorId;
    private String courseName;
    private String courseDescription;
    private double courseFee;
    private int studentCount;

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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
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
