package com.spaapp.model.services.spaservice;

import com.spaapp.model.services.spaservice.exception.CustomerServiceException;
import com.spaapp.persistence.DatabaseInitializer;
import com.spaapp.model.domain.Customer;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements ICustomerService {

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "SELECT * FROM customers";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int customer_id = resultSet.getInt("customer_id");
                        String username = resultSet.getString("username");
                        String address = resultSet.getString("address");
                        String phoneNumber = resultSet.getString("phoneNumber");
                        String email = resultSet.getString("email");
                        String legalName = resultSet.getString("legalName");

                        Customer customer = new Customer(customer_id, username, address, phoneNumber, email, legalName);
                        allCustomers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
        }

        return allCustomers;
    }

    @Override
    public void addCustomer(Customer customer) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "INSERT INTO customers (username, address, phoneNumber, email, legalName) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getAddress());
                preparedStatement.setString(3, customer.getPhoneNumber());
                preparedStatement.setString(4, customer.getEmail());
                preparedStatement.setString(5, customer.getLegalName());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(int customerId, Customer updatedCustomer) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "UPDATE customers SET address = ?, phoneNumber = ?, email = ?, legalName = ? WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedCustomer.getAddress());
                preparedStatement.setString(2, updatedCustomer.getPhoneNumber());
                preparedStatement.setString(3, updatedCustomer.getEmail());
                preparedStatement.setString(4, updatedCustomer.getLegalName());
                preparedStatement.setInt(5, customerId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "DELETE FROM customers WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
