package com.flipkart.exceptions;

public class StudentAlreadyGradedException extends Exception{
    private int studentId;
    private int courseId;

    public StudentAlreadyGradedException(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public String getMessage() {
        return "Student "+studentId+" is already graded in this course "+courseId;
    }
}
