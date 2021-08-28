package com.flipkart.exceptions;

public class StudentAlreadyRegisteredForSemesterException extends Exception {


    @Override
    public String getMessage() {
        return "Student already registered for the semester";
    }
}
