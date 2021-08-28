package com.flipkart.exceptions;

public class CourseNotSelectedException extends Exception{
    private int courseId;

    public CourseNotSelectedException(int courseId) {
        this.courseId = courseId;
    }
    @Override
    public String getMessage() {
        return "Course with Course ID: " + courseId + " not selected by the professor.";
    }
}
