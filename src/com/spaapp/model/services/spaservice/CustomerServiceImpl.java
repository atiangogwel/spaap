package com.spaapp.model.services.spaservice;
import com.spaapp.model.services.spaservice.exception.CustomerServiceException;

import com.spaapp.model.domain.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {

    private Map<String, Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getUsername())) {
            throw new CustomerServiceException("Customer with username " + customer.getUsername() + " already exists.");
        }
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customers.get(username);
    }

    @Override
    public void updateCustomer(String username, Customer updatedCustomer) {
        if (customers.containsKey(username)) {
            customers.put(username, updatedCustomer);
        } else {
            throw new CustomerServiceException("Customer not found with username: " + username);
        }
    }

    @Override
    public void deleteCustomer(String username) {
        if (customers.containsKey(username)) {
            customers.remove(username);
        } else {
            throw new CustomerServiceException("Customer not found with username: " + username);
        }
    }
}