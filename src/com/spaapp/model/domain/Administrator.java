package com.spaapp.model.domain;

public class Administrator {
    private String adminUsername;
    private String adminPassword;

    public Administrator(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }


    public void addService() {
        // logic to add a spa service
    }

    public void removeService() {
        // logic to remove a spa service
    }

    public void manageStaff() {
        // logic to manage spa staff
    }


    @Override
    public String toString() {
        return "Administrator: " + adminUsername;
    }
}
