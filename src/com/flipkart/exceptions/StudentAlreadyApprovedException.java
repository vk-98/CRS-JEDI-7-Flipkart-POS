package com.flipkart.exceptions;

/**
 * Exception to check if the Student is already approved
 */
public class StudentAlreadyApprovedException extends Exception {
    private int studentId;

    public StudentAlreadyApprovedException(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter function for StudentId
     * @return
     */
    public int getStudentId() {
        return this.studentId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "studentId: " + studentId + " already approved!";
    }
}
