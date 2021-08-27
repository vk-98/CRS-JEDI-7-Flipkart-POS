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

    /**
     * getter function for courseId
     * @return
     */
    public int getCourseId() {
        return courseId;
    }
}
