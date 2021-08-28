package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * SeatNotAvailableException
 */
public class SeatNotAvailableException extends Exception {
    private int courseId;

    /**
     * Parameterized Constructor
     *
     * @param courseId: courseId
     */
    public SeatNotAvailableException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * getter function for courseId
     *
     * @return returns courseId
     */
    public int getCourseId() {
        return courseId;
    }
}
