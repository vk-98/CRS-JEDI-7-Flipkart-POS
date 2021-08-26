package com.flipkart.exceptions;

public class StudentAlreadyApprovedException extends Exception {
    private int studentId;

    public StudentAlreadyApprovedException(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return this.studentId;
    }

    @Override
    public String getMessage() {
        return "studentId: " + studentId + " already approved!";
    }
}
