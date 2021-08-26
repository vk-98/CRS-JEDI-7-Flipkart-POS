package com.flipkart.exceptions;

/**
 * Exception to check if total number of students registered for the course is with in the range or not
 */
public class CourseCountException extends Exception{
    private String courseType;
    private int courseCount;
    private int requiredCourseCount;

    public CourseCountException(String courseType, int courseCount, int requiredCourseCount) {
        this.courseType = courseType;
        this.courseCount = courseCount;
        this.requiredCourseCount = requiredCourseCount;
    }

    public String getCourseType() {
        return courseType;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public int getRequiredCourseCount() {
        return requiredCourseCount;
    }
}
