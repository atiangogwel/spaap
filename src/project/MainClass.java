package project;

import com.spaapp.model.services.factory.ServiceFactory;
import com.spaapp.model.business.manager.SpaServiceManager;
import com.spaapp.model.domain.Customer;
import com.spaapp.model.domain.SpaService;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;
import com.spaapp.model.services.spaservice.ILoginService;
import com.spaapp.model.services.spaservice.ISpaService;
import com.spaapp.model.services.spaservice.LoginServiceImpl.AuthResult;
import com.spaapp.model.services.spaservice.SpaServiceImpl;
import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.domain.SpaStaff;
import com.spaapp.persistence.DatabaseInitializer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream; 
import java.io.InputStream;
import java.lang.reflect.Constructor; 
import java.lang.reflect.InvocationTargetException;
import java.util.List;



public class MainClass {
    // Initialize a logger for logging
    private static final Logger logger = Logger.getLogger(MainClass.class.getName());
    private static SpaServiceManager spaServiceManager;
    
    public static void main(String[] args) {
    	DatabaseInitializer.initializeDatabase();
        // Initialize logging
        configureLogging();
        // Load properties
        loadProperties();

        try {
            // Initialization
            ServiceFactory serviceFactory = new ServiceFactory();
            ILoginService loginService = serviceFactory.createLoginService();
            spaServiceManager = new SpaServiceManager();

            // Prompt the user for username and password
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            AuthResult authResult = loginService.authenticateCustomer(username, password);


            if (authResult.isAuthenticated()) {
                System.out.println("Authentication successful");
                // Check if the authenticated user is an admin
                if ("administrator".equalsIgnoreCase(authResult.getRole())) {
                    // Admin-specific logic here
                    System.out.println("Welcome, Admin!");
                    spaServiceManager = new SpaServiceManager(); 
                    while (true) {
                        // Display the entire menu for the administrator
                        displayMenu(scanner);
                }} else {
                    // Regular user logic here
                    System.out.println("Welcome, User!");
                    
                    while (true) {
                        // Display a limited menu for regular users
                        displayLimitedMenu(scanner);
                    }
                }         

            } else {
            	 System.out.println("Authentication failed. Exiting.");                
            }
        } catch (Exception e) {
            // Handle unexpected exceptions
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
    }
    //main menu
    private static void displayMenu(Scanner scanner) {
    	
        System.out.println("Select an option:");
        System.out.println("1. Add a new spa service");
        System.out.println("2. Update a spa service by ID");
        System.out.println("3. Delete a spa service by ID");
        System.out.println("4. View/List spa services available");
        System.out.println("5. Manage Spa Staff");
        System.out.println("6. Manage Customers");
        System.out.println("7. Exit");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                // Add a new spa service logic
                if (spaServiceManager == null) {
                    System.out.println("Error: SpaServiceManager is not initialized.");
                    break;
                }
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

                // Call the createSpaService method to add a new spa service
                spaServiceManager.createSpaService(serviceName, description, price);
                System.out.println("New spa service added successfully!");
                break;
            case "2":
                // Update a spa service logic
            	updateSpaService(scanner, spaServiceManager);
                break;
            case "3":
            	deleteSpaService(scanner, spaServiceManager);
                break;
            case "4":
                // View/List spa services available logic
                spaServiceManager.listAllSpaServices();
                break;
            case "5":
                // Manage Spa Staff logic
                break;
            case "6":
            	manageCustomers(scanner);
                break;
            case "7":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
        }
    	
    }
    private static void updateSpaService(Scanner scanner, ISpaService spaServiceManager) {
    	spaServiceManager.listAllSpaServices();
    	// Ask the user for the service ID to update
        System.out.print("Enter the ID of the service to update: ");
        int serviceIdToUpdate = Integer.parseInt(scanner.nextLine());

        // Check if the service exists
        SpaService existingService = spaServiceManager.readSpaService(serviceIdToUpdate);
        if (existingService != null) {
            // Ask the user for the new description and price
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
                System.out.println("Failed to update service.");
            }
        } else {
            System.out.println("Service not found. Please make sure to enter a valid service ID.");
        }
    }
    private static void deleteSpaService(Scanner scanner, ISpaService spaServiceManager) {
    	spaServiceManager.listAllSpaServices();
    	// Ask the user for the ID of the spa service to delete
        System.out.print("Enter the ID of the spa service to delete: ");
        int serviceIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Call the deleteSpaService method to delete the spa service
        boolean isDeleted = spaServiceManager.deleteSpaService(serviceIdToDelete);

        if (isDeleted) {
            System.out.println("");
        } else {
            System.out.println("Failed to delete spa service. Service not found.");
        }
    }

	private static void displayLimitedMenu(Scanner scanner) {
        // Display a limited menu for regular users
        System.out.println("Select an option:");
        System.out.println("1. View available spa services");
        System.out.println("2. Update personal information");
        System.out.println("3. Exit");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
            	 spaServiceManager.listAllSpaServices();
                break;
            case "2":
                // Update personal information logic
                break;
            case "3":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
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

    private static void manageCustomers(Scanner scanner)  {
    	ICustomerService customerService = new CustomerServiceImpl();
    	boolean exit = false;
    	
        System.out.println("Select an option:");
        System.out.println("1. View all customers");
        System.out.println("2. Add new customer information");
        System.out.println("3. Update customer information");
        System.out.println("4. Delete customer");
        System.out.println("5. Exit");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                // View all customers logic
            	   List<Customer> allCustomers = customerService.getAllCustomers();
            	    System.out.println("All Customers:");
            	    System.out.printf("%-5s | %-20s | %-50s | %-15s | %-30s | %-20s%n",
            	            "ID", "Username", "Address", "Phone Number", "Email", "Legal Name");
            	    System.out.println("--------------------------------------------------------------------------------------");

            	    for (Customer customer : allCustomers) {
            	        System.out.printf("%-5d | %-20s | %-50s | %-15s | %-30s | %-20s%n",
            	                customer.getCustomerId(), customer.getUsername(), customer.getAddress(),
            	                customer.getPhoneNumber(), customer.getEmail(), customer.getLegalName());
            	    }

                break;
            case "2":
            	addNewCustomer(customerService, scanner);
                break;
            case "3":
         	   List<Customer> allCustomersForDisplay1 = customerService.getAllCustomers();

       	    // Display the customers in tabular format
               System.out.println("Available Customers:");
               System.out.printf("%-5s | %-20s | %-50s | %-15s | %-30s | %-20s%n",
                       "ID", "Username", "Address", "Phone Number", "Email", "Legal Name");
               System.out.println("-------------------------------------------------------------------------------------------------------------------");

               for (Customer customer : allCustomersForDisplay1) {
                   System.out.printf("%-5d | %-20s | %-50s | %-15s | %-30s | %-20s%n",
                           customer.getCustomerId(), customer.getUsername(), customer.getAddress(),
                           customer.getPhoneNumber(), customer.getEmail(), customer.getLegalName());
               }
            	System.out.print("Enter the customer ID to update: ");
                int customerIdToUpdate = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Prompt the user for the updated customer information
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();

                System.out.print("Enter new phone number: ");
                String newPhoneNumber = scanner.nextLine();

                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();

                System.out.print("Enter new legal name: ");
                String newLegalName = scanner.nextLine();

                // Create a Customer object with the updated information
                Customer updatedCustomer = new Customer(null, newAddress, newPhoneNumber, newEmail, newLegalName);

                // Call the updateCustomer method
                customerService.updateCustomer(customerIdToUpdate, updatedCustomer);

                // Inform the user about the update
                System.out.println("Customer information updated successfully!");
                break;
            case "4":
                List<Customer> allCustomersForDisplay = customerService.getAllCustomers();

                // Display the customers in tabular format
                System.out.println("Available Customers:");
                System.out.printf("%-5s | %-20s | %-50s | %-15s | %-30s | %-20s%n",
                        "ID", "Username", "Address", "Phone Number", "Email", "Legal Name");
                System.out.println("-------------------------------------------------------------------------------------------------------------------");

                for (Customer customer : allCustomersForDisplay) {
                    System.out.printf("%-5d | %-20s | %-50s | %-15s | %-30s | %-20s%n",
                            customer.getCustomerId(), customer.getUsername(), customer.getAddress(),
                            customer.getPhoneNumber(), customer.getEmail(), customer.getLegalName());
                }
            	Scanner scannerDelete = new Scanner(System.in);

                // Prompt the user for the customer ID to delete
                System.out.print("Enter the customer ID to delete: ");
                int customerIdToDelete = scannerDelete.nextInt();
                scannerDelete.nextLine(); // Consume the newline character

                // Call the deleteCustomer method
                customerService.deleteCustomer(customerIdToDelete);
                System.out.println("Customer deleted successfully!");
                break;
            case "5":
                System.out.println("Going back to Main Menu.");
                exit = true;
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
        }   
    }
    private static void addNewCustomer(ICustomerService customerService, Scanner scanner) {
        System.out.println("Enter new customer details:");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Legal Name: ");
        String legalName = scanner.nextLine();

        Customer newCustomer = new Customer(username, address, phoneNumber, email, legalName);

        try {
            customerService.addCustomer(newCustomer);
            System.out.println("New customer added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add customer. Error: " + e.getMessage());
        }
    }

 // Generic method to create an instance of a class
    private static <T> T createInstance(String className, Class<T> type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        return type.cast(constructor.newInstance());
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
            ILoginService loginService = createInstance(loginServiceImplClassName, ILoginService.class);
            SpaServiceManager spaServiceManager = createInstance(spaServiceManagerImplClassName, SpaServiceManager.class);
            SpaStaffServiceImpl spaStaffService = createInstance(spaStaffServiceImplClassName, SpaStaffServiceImpl.class);

            // Set up customer service implementation
            ICustomerService customerService = createInstance(customerServiceImplClassName, ICustomerService.class);

            logger.setLevel(Level.INFO);
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
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
