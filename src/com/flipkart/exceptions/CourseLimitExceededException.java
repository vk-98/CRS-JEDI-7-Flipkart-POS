package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * CourseLimitExceededException
 */

public class CourseLimitExceededException extends Exception {

    private int num;

    /**
     * Constructor
     *
     * @param num: number of courses
     */
    public CourseLimitExceededException(int num) {
        this.num = num;
    }

    /**
     * Message returned when exception is thrown
     *
     * @return Returns Message
     */
    @Override
    public String getMessage() {
        return "You have registered for " + num + " courses but the total should be 6";
    }


}