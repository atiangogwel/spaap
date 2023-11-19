package com.spaapp.model.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.spaapp.model.domain.Customer;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;

public class CustomerServiceTest {

    private ICustomerService customerService;

    @Before
    public void setUp() {
        // Initialize the customer service before each test
        customerService = new CustomerServiceImpl();
    }

    @Test
    public void testAddAndGetCustomer() {
        Customer customer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe", "password123");

        // Test adding a customer
        customerService.addCustomer(customer);

        // Test getting the customer by username
        Customer retrievedCustomer = customerService.getCustomerByUsername("john_doe");
        assertEquals(customer, retrievedCustomer);
    }

    @Test
    public void testUpdateCustomer() {
        // Add a customer
        Customer initialCustomer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe", "password123");
        customerService.addCustomer(initialCustomer);

        // Update the customer
        Customer updatedCustomer = new Customer("John Doe", "456 Oak St", "987-654-3210", "john@example.com", "john_doe", "newpassword");
        customerService.updateCustomer("john_doe", updatedCustomer);

        // Test getting the updated customer
        Customer retrievedCustomer = customerService.getCustomerByUsername("john_doe");
        assertEquals(updatedCustomer, retrievedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        // Add a customer
        Customer customer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe", "password123");
        customerService.addCustomer(customer);

        // Delete the customer
        customerService.deleteCustomer("john_doe");

        // Test getting the deleted customer
        Customer retrievedCustomer = customerService.getCustomerByUsername("john_doe");
        assertNull(retrievedCustomer);
    }
}

