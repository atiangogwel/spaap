package com.spaapp.model.services.spaservice;

import com.spaapp.model.services.spaservice.exception.LoginServiceException;
import com.spaapp.persistence.DatabaseInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements ILoginService {

    public static class AuthResult {
        private final boolean isAuthenticated;
        private final Integer role_id;

        public AuthResult(boolean isAuthenticated, Integer role_id) {
            this.isAuthenticated = isAuthenticated;
            this.role_id = role_id;
        }
        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public Integer getRole() {
            return role_id;
        }
    }

    @Override
    public AuthResult authenticateCustomer(String username, String password) throws LoginServiceException {
        final String jdbcUrl = DatabaseInitializer.getJdbcUrl();

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Check if the user exists in the customers table
            AuthResult customerAuthResult = authenticateUser(connection, "customers", username, password);
            if (customerAuthResult.isAuthenticated()) {
                return customerAuthResult;
            }

            // Check if the user exists in the staff table
            AuthResult staffAuthResult = authenticateUser(connection, "staff", username, password);
            if (staffAuthResult.isAuthenticated()) {
                return staffAuthResult;
            }

            // Check if the user exists in the admins table
            AuthResult adminAuthResult = authenticateUser(connection, "admins", username, password);
            if (adminAuthResult.isAuthenticated()) {
                return adminAuthResult;
            }

            // If no match is found in any table
            return new AuthResult(false, null);

        } catch (SQLException e) {
            // Handle database connection or query errors
            throw new LoginServiceException("Error during login authentication");
        }
    }

    private AuthResult authenticateUser(Connection connection, String tableName, String username, String password)
            throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer role_id = resultSet.getInt("role_id");
                    return new AuthResult(true, role_id);
                } else {
                    return new AuthResult(false, null);
                }
            }
        }
    }
}
