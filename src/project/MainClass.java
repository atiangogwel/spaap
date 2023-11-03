package project;

import com.spaapp.model.services.loginservice.ILoginService;
import com.spaapp.model.services.factory.ServiceFactory;
import com.spaapp.model.domain.Customer;
import com.spaapp.model.services.spaservice.SpaServiceManager;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class MainClass {
    // Initialize a logger for logging
    private static final Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        // Initialize logging
        configureLogging();

        try {
            // Initialization
            ServiceFactory serviceFactory = new ServiceFactory();
            ILoginService loginService = serviceFactory.createLoginService();

            // Application logic
            boolean isAuthenticated = loginService.authenticateCustomer("user1", "pass123");

            if (isAuthenticated) {
                System.out.println("Authentication successful");
            } else {
                System.out.println("Authentication failed");
            }

            // Create and manage spa services
            SpaServiceManager spaServiceManager = new SpaServiceManager();

            // Display a heading for available spa services
            System.out.println("Available Spa Services:");

            while (true) {
                // List all available spa services
                spaServiceManager.listAllSpaServices();

                // Prompt the user for options
                System.out.println("Select an option:");
                System.out.println("1. Add a new spa service");
                System.out.println("2. Update a spa service by ID");
                System.out.println("3. Delete a spa service by ID");
                System.out.println("4. Exit");

                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();

                switch (userInput) {
                    case "1":
                        // Ask the user for service details
                        System.out.print("Enter service name: ");
                        String serviceName = scanner.nextLine();
                        System.out.print("Enter service description: ");
                        String description = scanner.nextLine();
                        
                        double price = 0;
                        boolean validPrice = false;
                        
                        while (!validPrice) {
                            System.out.print("Enter service price: ");
                            String priceInput = scanner.nextLine();
                            
                            try {
                                price = Double.parseDouble(priceInput);
                                validPrice = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid price format. Please enter a valid number.");
                            }
                        }

                        // Create the new spa service
                        spaServiceManager.createSpaService(serviceName, description, price);
                        System.out.println("New spa service added successfully!");
                        break;
                    case "2":
                        // Ask the user for the service ID to update
                        System.out.print("Enter the ID of the service to update: ");
                        int serviceIdToUpdate = Integer.parseInt(scanner.nextLine());

                        // Check if the service exists
                        if (spaServiceManager.readSpaService(serviceIdToUpdate) != null) {
                            System.out.print("Enter new description: ");
                            String newDescription = scanner.nextLine();
                            
                            double newPrice = 0;
                            boolean validNewPrice = false;

                            while (!validNewPrice) {
                                System.out.print("Enter new price: ");
                                String newPriceInput = scanner.nextLine();

                                try {
                                    newPrice = Double.parseDouble(newPriceInput);
                                    validNewPrice = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid price format. Please enter a valid number.");
                                }
                            }

                            // Update the spa service
                            if (spaServiceManager.updateSpaService(serviceIdToUpdate, newDescription, newPrice)) {
                                System.out.println("Service updated successfully!");
                            } else {
                                System.out.println("Service not found.");
                            }
                        } else {
                            System.out.println("Service not found.");
                        }
                        break;
                    case "3":
                        // Ask the user for the service ID to delete
                        System.out.print("Enter the ID of the service to delete: ");
                        int serviceIdToDelete = Integer.parseInt(scanner.nextLine());

                        // Check if the service exists
                        if (spaServiceManager.readSpaService(serviceIdToDelete) != null) {
                            // Delete the spa service
                            if (spaServiceManager.deleteSpaService(serviceIdToDelete)) {
                                System.out.println("Service deleted successfully!");
                            } else {
                                System.out.println("Service not found.");
                            }
                        } else {
                            System.out.println("Service not found.");
                        }
                        break;
                    case "4":
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            }
        } catch (Exception e) {
            // Handle unexpected exceptions
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
    }

    // Method to configure logging
    private static void configureLogging() {
        // Set up logging configuration (e.g., log to a file or console)
        // Here, we'll configure basic console logging
        logger.setLevel(Level.INFO);
    }
}
