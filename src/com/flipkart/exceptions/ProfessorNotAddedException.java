package com.flipkart.exceptions;

public class ProfessorNotAddedException extends Exception {
    private int professorId;

    public ProfessorNotAddedException(int professorId) {
        this.professorId = professorId;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    @Override
    public String getMessage() {
        return "professorId: " + professorId + " not added!";
    }
}
