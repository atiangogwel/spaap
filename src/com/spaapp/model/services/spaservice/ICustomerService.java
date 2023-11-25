package com.spaapp.model.services.spaservice;

import com.spaapp.model.domain.Customer;

import java.util.List;

public interface ICustomerService {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

	void updateCustomer(int customerId, Customer updatedCustomer);

	void deleteCustomer(int customerId);
}
