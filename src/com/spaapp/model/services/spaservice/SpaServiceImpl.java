package com.spaapp.model.services.spaservice;

import com.spaapp.model.domain.SpaService;
import com.spaapp.persistence.DatabaseInitializer;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaServiceImpl implements ISpaService {
    private List<SpaService> spaServices;
    private int nextServiceId;
    // Database connection URL
    final String jdbcUrl = DatabaseInitializer.getJdbcUrl(); 


    public SpaServiceImpl() {
        spaServices = new ArrayList<>();
        nextServiceId = 1;
    }

    @Override
    public void createSpaService(String serviceName, String description, double price) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Insert the new spa service into the database
            String insertQuery = "INSERT INTO spa_services (service_name, description, price) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, serviceName);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, price);
                preparedStatement.executeUpdate();
            }

            // Fetch the ID of the newly inserted service
            int newServiceId;
            String selectIdQuery = "SELECT last_insert_rowid()";
            try (PreparedStatement idStatement = connection.prepareStatement(selectIdQuery);
                 ResultSet resultSet = idStatement.executeQuery()) {
                newServiceId = resultSet.getInt(1);
            }

            // Create a new SpaService object and add it to the list
            SpaService newService = new SpaService(newServiceId, serviceName, description, price);
            spaServices.add(newService);
            nextServiceId++;

        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
        }
    }


    @Override
    public boolean updateSpaService(int serviceId, String newDescription, double newPrice) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Check if the spa service with the given ID exists
            SpaService existingService = readSpaService(serviceId);

            if (existingService != null) {
                System.out.println("Found existing service: " + existingService);

                String query = "UPDATE spa_services SET description = ?, price = ? WHERE service_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, newDescription);
                    preparedStatement.setDouble(2, newPrice);
                    preparedStatement.setInt(3, serviceId);

                    // Execute the update query
                    int rowsAffected = preparedStatement.executeUpdate();

                    // Check if the update was successful
                    if (rowsAffected > 0) {
                        System.out.println("Service updated successfully!");
                        return true;
                    } else {
                        System.out.println("Update failed. No rows affected.");
                        return false;
                    }
                }
            } else {
                // Service not found
                System.out.println("Service not found for ID: " + serviceId);
                return false;
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteSpaService(int serviceId) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Check if the spa service with the given ID exists
            SpaService existingService = readSpaService(serviceId);

            if (existingService != null) {
                System.out.println("Found existing service: " + existingService);

                // Delete the spa service from the database
                String query = "DELETE FROM spa_services WHERE service_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, serviceId);

                    // Execute the delete query
                    int rowsAffected = preparedStatement.executeUpdate();

                    // Check if the delete was successful
                    if (rowsAffected > 0) {
                        System.out.println("Service deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Delete failed. No rows affected.");
                        return false;
                    }
                }
            } else {
                // Service not found
                System.out.println("Service not found for ID: " + serviceId);
                return false;
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void listAllSpaServices() {
        // get available spa services
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();
        List<SpaService> spaServices = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "SELECT * FROM spa_services";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int serviceId = resultSet.getInt("service_id");
                        String serviceName = resultSet.getString("service_name");
                        String description = resultSet.getString("description");
                        double price = resultSet.getDouble("price");

                        SpaService spaService = new SpaService(serviceId, serviceName, description, price);
                        spaServices.add(spaService);
                    }
                }
            }

            // Display the spa services in tabular format
            System.out.println("Available Spa Services:");
            System.out.printf("%-5s | %-20s | %-50s | %-10s%n", "ID", "Name", "Description", "Price");
            System.out.println("---------------------------------------------------------------");

            for (SpaService spaService : spaServices) {
                System.out.printf("%-5d | %-20s | %-50s | %-10.2f%n",
                        spaService.getServiceId(), spaService.getServiceName(),
                        spaService.getDescription(), spaService.getPrice());
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
        }
    }

    
    @Override
    public SpaService readSpaService(int serviceId) {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String query = "SELECT * FROM spa_services WHERE service_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, serviceId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int retrievedServiceId = resultSet.getInt("service_id");
                        String serviceName = resultSet.getString("service_name");
                        String description = resultSet.getString("description");
                        double price = resultSet.getDouble("price");

                        return new SpaService(retrievedServiceId, serviceName, description, price);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
        }

        return null; // Service not found
    }

   }


