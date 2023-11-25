package com.spaapp.model.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.spaapp.model.domain.Customer;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;
import com.spaapp.persistence.DatabaseInitializer;

public class CustomerServiceTest {
    private ICustomerService customerService;

    @Before
    public void setUp() {
        try {
            // Initialize the testing database before each test
            DatabaseInitializer.initializeDatabase();
            customerService = new CustomerServiceImpl();
        } catch (Exception e) {
            // Handle exceptions (log or rethrow as a RuntimeException)
            e.printStackTrace();
            throw new RuntimeException("Error initializing database for testing.", e);
        }
    }

    @Test
    public void testAddAndGetCustomer() {
        Customer customer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe");

        // Test adding a customer
        customerService.addCustomer(customer);

    }

    @Test
    public void testUpdateCustomer() {
        // Add a customer
        Customer initialCustomer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe");
        customerService.addCustomer(initialCustomer);

        // Update the customer
        Customer updatedCustomer = new Customer("John Doe", "456 Oak St", "987-654-3210", "john@example.com", "john_doe");
        customerService.updateCustomer(1, updatedCustomer); // Assuming the customer_id is 1


    }

    @Test
    public void testUpdateNonexistentCustomer() {
        // Attempt to update a customer that doesn't exist
        Customer updatedCustomer = new Customer("Nonexistent User", "456 Oak St", "987-654-3210", "nonexistent@example.com", "nonexistent_user");
        customerService.updateCustomer(999, updatedCustomer); // Assuming the customer_id doesn't exist

    }

    @Test
    public void testDeleteCustomer() {
        // Add a customer
        Customer customer = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe");
        customerService.addCustomer(customer);

        // Delete the customer
        customerService.deleteCustomer(1);

    }

    @Test
    public void testDeleteNonexistentCustomer() {
        // Attempt to delete a nonexistent customer
        customerService.deleteCustomer(999); // Assuming the customer_id doesn't exist

    }
}
