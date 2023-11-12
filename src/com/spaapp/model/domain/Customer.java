package com.spaapp.model.domain;
import java.util.Objects;

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
        // booking logic
    }

    public void updateProfile() {
        // profile update logic
    }


    @Override
    public String toString() {
        return "Customer: " + legalName + " (" + username + ")";
    }


	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
