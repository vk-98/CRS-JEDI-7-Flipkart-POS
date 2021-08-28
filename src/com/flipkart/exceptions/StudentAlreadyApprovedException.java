package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * StudentAlreadyApprovedException
 */
public class StudentAlreadyApprovedException extends Exception {
    private int studentId;

    /**
     * Parameterized Constructor
     *
     * @param studentId Unique Id of the Student
     */
    public StudentAlreadyApprovedException(int studentId) {
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
        return "studentId: " + studentId + " already approved!";
    }
}
