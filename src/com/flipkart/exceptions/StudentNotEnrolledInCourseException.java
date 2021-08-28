package com.flipkart.exceptions;

public class StudentNotEnrolledInCourseException extends Exception{
    private int courseId;
    private int studentId;

    public StudentNotEnrolledInCourseException(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Student "+studentId+" is not enrolled in this course "+courseId;
    }
}
