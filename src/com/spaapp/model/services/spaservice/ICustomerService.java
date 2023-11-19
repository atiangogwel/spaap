package com.spaapp.model.services.spaservice;
import com.spaapp.model.domain.Customer;

public interface ICustomerService {

    void addCustomer(Customer customer);

    Customer getCustomerByUsername(String username);

    void updateCustomer(String username, Customer updatedCustomer);

    void deleteCustomer(String username);
}