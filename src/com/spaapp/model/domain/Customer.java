package com.spaapp.model.domain;

import java.util.Objects;

public class Customer {
    private int customer_id;
    private String legalName;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    public Customer(int customer_id, String username, String address, String phoneNumber, String email, String legalName) {
        this.customer_id = customer_id;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.legalName = legalName;
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

    public int getCustomerId() {
        return customer_id;
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
}