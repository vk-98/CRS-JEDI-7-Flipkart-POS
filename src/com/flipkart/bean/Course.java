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

    /**
     * Parameterized Constructor
     *
     * @param courseName        Name of the Course
     * @param courseDescription Description of the course
     * @param courseFee         Fee assigned to a course
     */
    public Course(String courseName, String courseDescription, double courseFee) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseFee = courseFee;
        this.studentCount = 0;
    }

    /**
     * Parameterized Constructor
     *
     * @param professorId       Unique Id of the Professor
     * @param courseName        Name of the Course
     * @param courseDescription Description of the course
     * @param courseFee         Fee assigned to a course
     */
    public Course(int professorId, String courseName, String courseDescription, double courseFee) {
        this.professorId = professorId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseFee = courseFee;
        this.studentCount = 0;
    }

    /**
     * Getter for CourseDescription
     *
     * @return courseDescription
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * Setter For CourseDescription
     *
     * @param courseDescription Description of the course
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    /**
     * Getter for CourseId
     *
     * @return courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Setter For CourseId
     *
     * @param courseId Unique Id of the Course
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter for professorId
     *
     * @return professorId
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Setter For ProfessorId
     *
     * @param professorId Unique Id of the Professor
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter for CourseName
     *
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Setter For CourseName
     *
     * @param courseName Name of the Course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Getter for CourseFee
     *
     * @return courseFee
     */
    public double getCourseFee() {
        return courseFee;
    }

    /**
     * Setter for CourseFee
     *
     * @param courseFee Fee assigned to a Course
     */
    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    /**
     * Getter for StudentCount
     *
     * @return studentCount
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Setter For Student Count
     *
     * @param studentCount Count of the Students for a course
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
