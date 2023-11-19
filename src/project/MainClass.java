package project;

import com.spaapp.model.services.factory.ServiceFactory;
import com.spaapp.model.business.manager.SpaServiceManager;
import com.spaapp.model.domain.Customer;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;
import com.spaapp.model.services.spaservice.ILoginService;
import com.spaapp.model.services.spaservice.ISpaService;
import com.spaapp.model.services.spaservice.SpaServiceImpl;
import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.domain.SpaStaff;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream; 
import java.io.InputStream; 


public class MainClass {
    // Initialize a logger for logging
    private static final Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        // Initialize logging
        configureLogging();
        // Load properties
        loadProperties();

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
            ISpaService spaServiceManager = new SpaServiceImpl();

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
                System.out.println("4. Manage Spa Staff");
                System.out.println("5. Manage Customers");
                System.out.println("6. Exit");

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
                        // Call the method to manage spa staff
                        manageSpaStaff();
                        break;
                    case "5":
                        // Call the method to manage customers
                        manageCustomers(new CustomerServiceImpl());
                        break;
                    case "6":
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
    
    //staff management service/CRUD operations
    private static void manageSpaStaff() {
        SpaStaffServiceImpl spaStaffService = new SpaStaffServiceImpl();

        // Add spa staff
        SpaStaff staff1 = new SpaStaff("John Doe", "Massage Therapist");
        spaStaffService.addSpaStaff(staff1);
        System.out.println("Spa Staff created: " + staff1);

        // Read spa staff
        SpaStaff readStaff = spaStaffService.getSpaStaff("John Doe");
        if (readStaff != null) {
            System.out.println("Read Spa Staff: " + readStaff);
        }

        // Update spa staff
        SpaStaff updatedStaff = new SpaStaff("John Doe", "Senior Massage Therapist");
        spaStaffService.updateSpaStaff("John Smith", updatedStaff);

        // Read the updated spa staff
        readStaff = spaStaffService.getSpaStaff("John Smith");
        if (readStaff != null) {
            System.out.println("Updated Spa Staff: " + readStaff);
        }

        // Delete spa staff
        spaStaffService.deleteSpaStaff("John Smith");

        // Try to read the deleted spa staff
        readStaff = spaStaffService.getSpaStaff("John Smith");
        if (readStaff == null) {
            System.out.println("Spa Staff 'John Doe' has been deleted.");
        }
    }
    
    //customer management service/CRUD operations

    private static void manageCustomers(ICustomerService customerService) {
        // Add customer
        Customer customer1 = new Customer("John Doe", "123 Main St", "123-456-7890", "john@example.com", "john_doe", "password123");
        customerService.addCustomer(customer1);
        System.out.println("Customer created: " + customer1);

        // Read customer
        Customer readCustomer = customerService.getCustomerByUsername("john_doe");
        if (readCustomer != null) {
            System.out.println("Read Customer: " + readCustomer);
        }

        // Update customer
        Customer updatedCustomer = new Customer("John Doe", "456 Oak St", "987-654-3210", "john@example.com", "john_doe", "newpassword");
        customerService.updateCustomer("john_doe", updatedCustomer);

        // Read the updated customer
        readCustomer = customerService.getCustomerByUsername("john_doe");
        if (readCustomer != null) {
            System.out.println("Updated Customer: " + readCustomer);
        }

        // Delete customer
        customerService.deleteCustomer("john_doe");

        // Try to read the deleted customer
        readCustomer = customerService.getCustomerByUsername("john_doe");
        if (readCustomer == null) {
            System.out.println("Customer 'John Doe' has been deleted.");
        }
    }

    // Method to configure logging
    private static void configureLogging() {
        try (InputStream input = new FileInputStream("config/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            // Get the values of the service properties
            String loginServiceImplClassName = properties.getProperty("login.service.impl");
            String spaServiceManagerImplClassName = properties.getProperty("spaServiceManager.impl");
            String spaStaffServiceImplClassName = properties.getProperty("spastaff.service.impl");
            String customerServiceImplClassName = properties.getProperty("customer.service.impl");

            // Initialize services
            ILoginService loginService = (ILoginService) Class.forName(loginServiceImplClassName).newInstance();
            SpaServiceManager spaServiceManager = (SpaServiceManager) Class.forName(spaServiceManagerImplClassName).newInstance();
            SpaStaffServiceImpl spaStaffService = (SpaStaffServiceImpl) Class.forName(spaStaffServiceImplClassName).newInstance();
            
            // Set up customer service implementation
            ICustomerService customerService = (ICustomerService) Class.forName(customerServiceImplClassName).newInstance();

            logger.setLevel(Level.INFO);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            // Handle exceptions...
            e.printStackTrace();
        }
    }



    private static void loadProperties() {
        try {
            // Get the prop_location system property
            String propLocation = System.getProperty("prop_location");

            // Check if prop_location is specified
            if (propLocation == null || propLocation.isEmpty()) {
                System.out.println("Error: prop_location not specified. Exiting.");
                System.exit(1);
            }

            // Load properties from the specified location
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream(propLocation)) {
                properties.load(input);
            }

            // Access the properties as needed
            String spaServiceImplementation = properties.getProperty("spa.service.implementation");
            System.out.println("Spa Service Implementation: " + spaServiceImplementation);

        } catch (IOException e) {
            // Handle exceptions related to property loading
            logger.log(Level.SEVERE, "Error loading properties: " + e.getMessage(), e);
            System.exit(1);
        }
    }
}
