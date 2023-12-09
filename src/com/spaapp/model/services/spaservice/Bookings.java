package com.spaapp.model.services.spaservice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spaapp.persistence.DatabaseInitializer;
import com.spaapp.model.domain.Booking;

public class Bookings {
    public static void bookSpaService(int customerId, int serviceId, String bookingDate) {
        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String insertQuery = "INSERT INTO bookings (customer_id, service_id, date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.setInt(2, serviceId);
                preparedStatement.setString(3, bookingDate);
                preparedStatement.executeUpdate();
                System.out.println("Booking successful!");
            }
        } catch (SQLException e) {
            System.out.println("Error booking spa service: " + e.getMessage());
        }
    }
    public static void viewBookings() {
        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String query = "SELECT bookings.id, customers.username, spa_services.service_name, bookings.date " +
                           "FROM bookings " +
                           "JOIN customers ON bookings.customer_id = customers.customer_id " +
                           "JOIN spa_services ON bookings.service_id = spa_services.service_id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print table header
                System.out.printf("%-5s%-20s%-20s%-15s%n", "ID", "Customer Name", "Service Name", "Booking Date");
                System.out.println("-----------------------------------------------");

                // Print each row in the table
                while (resultSet.next()) {
                    int bookingId = resultSet.getInt("id");
                    String customerName = resultSet.getString("username");
                    String serviceName = resultSet.getString("service_name");
                    String bookingDate = resultSet.getString("date");

                    System.out.printf("%-5d%-20s%-20s%-15s%n", bookingId, customerName, serviceName, bookingDate);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error viewing bookings: " + e.getMessage());
        }
    }
}
    
    
