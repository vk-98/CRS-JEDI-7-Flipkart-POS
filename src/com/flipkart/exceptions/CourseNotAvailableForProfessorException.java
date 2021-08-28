package com.flipkart.exceptions;

public class CourseNotAvailableForProfessorException extends Exception{
    private int courseId;

    public CourseNotAvailableForProfessorException(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getMessage() {
        return "Course with course Id: " + courseId + " cannot be selected.";
    }
}
