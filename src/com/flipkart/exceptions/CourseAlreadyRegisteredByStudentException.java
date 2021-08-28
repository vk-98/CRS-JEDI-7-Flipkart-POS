package com.flipkart.exceptions;

public class CourseAlreadyRegisteredByStudentException extends Exception{
    private int courseId;

    public CourseAlreadyRegisteredByStudentException(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getMessage() {
        return "Student has already registered for this course "+courseId;
    }
}
