package com.flipkart.constants;

/**
 * @author JEDI-07
 * Admin Client
 */
public enum Courses {
    MAX_PRIMARY(4), MAX_SECONDARY(2);

    private int value;

    private Courses(int i) {
        this.value = i;
    }
}
