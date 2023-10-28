package com.spaapp.model.domain;

public class Customer {
    private String legalName;
    private String homeAddress;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    public Customer(String legalName, String homeAddress, String phoneNumber, String email, String username, String password) {
        this.legalName = legalName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public void bookAppointment() {
        // Implement booking logic
    }

    public void updateProfile() {
        // Implement profile update logic
    }


    @Override
    public String toString() {
        return "Customer: " + legalName + " (" + username + ")";
    }
}
