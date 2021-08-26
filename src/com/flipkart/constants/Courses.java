package com.flipkart.constants;

/**
 * Enumeration class for Courses
 */
public enum Courses {
    MAX_PRIMARY(4), MAX_SECONDARY(2);

    private int value;

    private Courses(int i) {
        this.value = i;
    }
}
