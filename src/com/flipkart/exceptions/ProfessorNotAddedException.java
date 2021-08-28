package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * ProfessorNotAddedException
 */
public class ProfessorNotAddedException extends Exception {
    private int professorId;

    /**
     * Parameterized Constructor
     *
     * @param professorId Unique Id of the Professor
     */
    public ProfessorNotAddedException(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter for ProfessorId
     *
     * @return returns professorId
     */
    public int getProfessorId() {
        return this.professorId;
    }

    /**
     * Method for getting Exception Message
     *
     * @return returns Message
     */
    @Override
    public String getMessage() {
        return "professorId: " + professorId + " not added!";
    }
}
