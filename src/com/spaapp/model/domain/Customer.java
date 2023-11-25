package com.spaapp.model.domain;

import java.util.Objects;

public class Customer {
    private String legalName;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private String customer_id;

    public Customer(String username, String address, String phoneNumber, String email, String legalName) {
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.legalName = legalName;
    }

    public void bookAppointment() {
        // Booking logic
    }

    public void updateProfile() {
        // Profile update logic
    }

    @Override
    public String toString() {
        return "Customer: " + legalName + " (" + username + ")";
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLegalName() {
        return legalName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(username, customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public int getCustomerId() {
        // Convert the customer_id String to an int
        try {
            return Integer.parseInt(customer_id);
        } catch (NumberFormatException e) {

            return 0;
        }
    }
}
