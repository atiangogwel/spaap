package com.spaapp.model.services.spaservice;

import com.spaapp.model.services.spaservice.exception.LoginServiceException;
import com.spaapp.persistence.DatabaseInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginServiceImpl implements ILoginService {

    public static class AuthResult {
        private final boolean isAuthenticated;
        private final String role;

        public AuthResult(boolean isAuthenticated, String role) {
            this.isAuthenticated = isAuthenticated;
            this.role = role;
        }

        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public String getRole() {
            return role;
        }
    }

    @Override
    public AuthResult authenticateCustomer(String username, String password) {
    	final String jdbcUrl = DatabaseInitializer.getJdbcUrl(); 
    	try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Check if the user exists with the provided credentials
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String role = resultSet.getString("role");
                        return new AuthResult(true, role);
                    } else {
                        return new AuthResult(false, null);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query errors
            e.printStackTrace();
            return new AuthResult(false, null);
        }
    }
}

