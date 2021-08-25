package com.flipkart.bean;

public class Admin extends User {
    private String adminId;
    private String adminLevel;

    public Admin() {
    }

    public Admin(String userName, String userEmailId, String userPassword, String role, String phoneNo, String adminId, String adminLevel) {
        super(userName, userEmailId, userPassword, role, phoneNo);
        this.adminId = adminId;
        this.adminLevel = adminLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}
