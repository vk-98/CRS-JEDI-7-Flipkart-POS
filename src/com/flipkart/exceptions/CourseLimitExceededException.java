package com.flipkart.exceptions;

/**
 * Exception to check if the maximum number of registered courses is exceeded
 */
public class CourseLimitExceededException extends Exception{

    private int num;

    /**
     * Constructor
     * @param num number of courses
     */
    public CourseLimitExceededException(int num )
    {
        this.num = num;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage()
    {
        return "You have registered for " + num + " courses but the total should be 6";
    }


}