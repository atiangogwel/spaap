// SpaStaffServiceImpl.java
package com.spaapp.model.services.spaservice;

import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;
import com.spaapp.persistence.DatabaseInitializer;
import com.spaapp.model.domain.SpaStaff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpaStaffServiceImpl implements ISpaStaffService {
    @Override
    public void addSpaStaff(SpaStaff spaStaff) {
        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String query = "INSERT INTO staff (username, password, full_name, email, role_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, spaStaff.getUsername());
                preparedStatement.setString(2, spaStaff.getPassword());
                preparedStatement.setString(3, spaStaff.getFullName());
                preparedStatement.setString(4, spaStaff.getEmail());
                preparedStatement.setInt(5, 2); // Set role_id to 2
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SpaStaffServiceException("Error adding spa staff to the database");
        }
    }

    @Override
    public List<SpaStaff> getAllSpaStaff() {
        List<SpaStaff> spaStaffList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String query = "SELECT * FROM staff";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print table header
                System.out.printf("%-10s%-20s%-20s%-30s%-20s%n", "Staff ID", "Username", "Password", "Email", "Full Name");
                System.out.println("------------------------------------------------------------");

                while (resultSet.next()) {
                    SpaStaff spaStaff = new SpaStaff(
                            resultSet.getInt("staff_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("full_name")
                            // Add other fields as needed
                    );

                    // Print each row in the table
                    System.out.printf("%-10d%-20s%-20s%-30s%-20s%n",
                            spaStaff.getStaffId(),
                            spaStaff.getUsername(),
                            spaStaff.getPassword(),
                            spaStaff.getEmail(),
                            spaStaff.getFullName());

                    spaStaffList.add(spaStaff);
                }
            }
        } catch (SQLException e) {
            throw new SpaStaffServiceException("Error retrieving all spa staff from the database");
        }

        return spaStaffList;
    }

    @Override
    public void updateSpaStaff(int staffId, SpaStaff updatedStaff) {
        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String query = "UPDATE staff SET username = ?, password = ?, email = ? WHERE staff_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedStaff.getUsername());
                preparedStatement.setString(2, updatedStaff.getPassword());
                preparedStatement.setString(3, updatedStaff.getEmail());
                preparedStatement.setInt(4, staffId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SpaStaffServiceException("Staff with ID " + staffId + " not found.");
                }
            }
        } catch (SQLException e) {
            throw new SpaStaffServiceException("Error updating spa staff in the database");
        }
    }

    @Override
    public void deleteSpaStaff(int staffId) {
        try (Connection connection = DriverManager.getConnection(DatabaseInitializer.getJdbcUrl())) {
            String query = "DELETE FROM staff WHERE staff_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, staffId);
                
                int rowsAffected = preparedStatement.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SpaStaffServiceException("Staff with ID " + staffId + " not found.");
                }
            }
        } catch (SQLException e) {
            throw new SpaStaffServiceException("Error deleting spa staff from the database");
        }
    }

}







