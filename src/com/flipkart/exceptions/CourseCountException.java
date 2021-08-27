package com.flipkart.exceptions;

public class CourseCountException extends Exception{
    private String courseType;
    private int courseCount;
    private int requiredCourseCount;


    /**
     * Parameterized Constructor
     * @param courseType: type of course
     * @param courseCount: number of courses of the given courseType which are opted by the student
     * @param requiredCourseCount: number of courses of the given type to be chosen by the student
     */
    public CourseCountException(String courseType, int courseCount, int requiredCourseCount) {
        this.courseType = courseType;
        this.courseCount = courseCount;
        this.requiredCourseCount = requiredCourseCount;
    }

    /**
     * getter function for courseType
     * @return
     */
    public String getCourseType() {
        return courseType;
    }


    /**
     * getter function for courseCount
     * @return
     */
    public int getCourseCount() {
        return courseCount;
    }


    /**
     * getter function for requiredCourseCount
     * @return
     */
    public int getRequiredCourseCount() {
        return requiredCourseCount;
    }
}
