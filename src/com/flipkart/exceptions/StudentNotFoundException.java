package com.flipkart.exceptions;

/**
 * Exception thrown when student is not found
 * @author JEDI-03
 *
 */
public class StudentNotFoundException extends Exception{
    private int studentId;

    public StudentNotFoundException(int studentId) {
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
        return "studentId: " + studentId + " not found.";
    }
}
