package com.spaapp.persistence;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.nio.file.Files;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Get the root path of the project
            Path projectRoot = Paths.get(System.getProperty("user.dir"));

            // Define the database file name
            String databaseFileName = "spaapp.db";

            // Get the path for the database file within the project directory
            Path databasePath = projectRoot.resolve(databaseFileName);

            // JDBC URL for SQLite
            String jdbcUrl = "jdbc:sqlite:" + databasePath.toString();

            // Check if the database file exists
            if (!Files.exists(databasePath)) {
                System.out.println("Database file does not exist. Creating...");

                // Create the database file and parent directories if needed
                Files.createDirectories(databasePath.getParent());
                Files.createFile(databasePath);

                System.out.println("Database file created successfully.");
            }


        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static String getJdbcUrl() {
        // Get the root path of the project
        Path projectRoot = Paths.get(System.getProperty("user.dir"));

        // Define the database file name
        String databaseFileName = "spaapp.db";

        // Get the path for the database file within the project directory
        Path databasePath = projectRoot.resolve(databaseFileName);

        // JDBC URL for SQLite
        return "jdbc:sqlite:" + databasePath.toString();
    }

    
    
    
    
}