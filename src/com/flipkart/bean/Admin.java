package com.flipkart.bean;

public class Admin extends User {
    private int id;

    public Admin() {
    }

    public Admin(String userName, String userEmailId, String userPassword, String role, String phoneNo) {
        super(userName, userEmailId, userPassword, role, phoneNo);
    }

    public int getId() {
        return id;
    }

    public void setAId(int adminId) {
        this.id = id;
    }
}
