package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * StudentNotFoundException
 */
public class StudentNotFoundException extends Exception {
    private int studentId;

    /**
     * Parameterized Constructor
     *
     * @param studentId Unique Id of the Student
     */
    public StudentNotFoundException(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter for the studentId
     *
     * @return returns StudentId
     */
    public int getStudentId() {
        return this.studentId;
    }

    /**
     * Method for getting Exception Message
     *
     * @return returns Message
     */
    @Override
    public String getMessage() {
        return "studentId: " + studentId + " not found.";
    }
}
