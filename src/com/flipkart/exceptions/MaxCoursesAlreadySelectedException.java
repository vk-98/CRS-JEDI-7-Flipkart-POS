package com.flipkart.exceptions;

public class MaxCoursesAlreadySelectedException extends Exception {
    private int maxCourseCount;
    private String courseType;

    public MaxCoursesAlreadySelectedException(int maxCourseCount, String courseType) {
        this.maxCourseCount = maxCourseCount;
        this.courseType = courseType;
    }

    @Override
    public String getMessage() {
        return "You can only select "+maxCourseCount+" "+courseType+" courses";
    }
}
