package com.flipkart.exceptions;

public class NoRegisteredCourseException extends Exception{
    private String msg="No courses have been registered";

    @Override
    public String getMessage() {
        return msg;
    }
}
