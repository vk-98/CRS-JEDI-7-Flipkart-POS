package com.flipkart.exceptions;

public class CourseNotRegisteredByStudentException extends Exception{
    private int courseId;

    public CourseNotRegisteredByStudentException(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getMessage() {
        return "Student is not registered for this course id "+courseId;
    }
}
