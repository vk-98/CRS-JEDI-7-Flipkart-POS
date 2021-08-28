package com.flipkart.exceptions;

public class CourseNotSelectedExcpetion extends Exception{
    private int courseId;

    public CourseNotSelectedExcpetion(int courseId) {
        this.courseId = courseId;
    }
    @Override
    public String getMessage() {
        return "Course with Course ID: " + courseId + " not selected by the professor.";
    }
}
