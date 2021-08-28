package com.flipkart.exceptions;

public class SeatNotAvailableException extends Exception {
    private int courseId;

    /**
     * Parameterized Constructor
     * @param courseId: courseId
     */
    public SeatNotAvailableException(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getMessage() {
        return "Maximum limit of students for courseId " +courseId+" reached.";
    }
}
