package com.flipkart.exceptions;

/**
 * Exception to check if the professor is not added successfully by admin
 */
public class ProfessorNotAddedException extends Exception {
    private int professorId;

    public ProfessorNotAddedException(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter function for professorId
     * @return
     */
    public int getProfessorId() {
        return this.professorId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "professorId: " + professorId + " not added!";
    }
}
