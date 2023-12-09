package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;
import com.spaapp.model.services.spaservice.ISpaStaffService;
import com.spaapp.persistence.DatabaseInitializer;
import com.spaapp.model.domain.SpaStaff;

public class SpaStaffServiceImplTest {
    private ISpaStaffService spaStaffService;

    @Before
    public void setUp() {
        try {
            // Initialize the testing database before each test
            DatabaseInitializer.initializeDatabase();
            spaStaffService = new SpaStaffServiceImpl();
        } catch (Exception e) {
            // Handle exceptions (log or rethrow as a RuntimeException)
            e.printStackTrace();
            throw new RuntimeException("Error initializing database for testing.", e);
        }
    }

    @Test
    public void testAddAndGetSpaStaff() {
        SpaStaff spaStaff = new SpaStaff(10, "john_doe", "password", "john@example.com", "John Doe");

        // Test adding a spa staff
        spaStaffService.addSpaStaff(spaStaff);
    }

    @Test 
    public void testUpdateSpaStaff() {
        // Add a spa staff
        SpaStaff initialSpaStaff = new SpaStaff(7, "john_doe", "password", "john@example.com", "John Doe");
        spaStaffService.addSpaStaff(initialSpaStaff);

        // Update the spa staff
        SpaStaff updatedSpaStaff = new SpaStaff(7, "john_doe_updated", "new_password", "john@example.com", "John Doe");
        spaStaffService.updateSpaStaff(10, updatedSpaStaff);

        // Retrieve the updated spa staff
        List<SpaStaff> spaStaffList = spaStaffService.getAllSpaStaff();
        SpaStaff retrievedSpaStaff = spaStaffList.stream().filter(s -> s.getStaffId() == 7).findFirst().orElse(null);

        // Assert that the update was successful
        assertNotNull(retrievedSpaStaff);
        assertEquals(updatedSpaStaff, retrievedSpaStaff);
    }

    @Test
    public void testUpdateNonexistentSpaStaff() {
        // Attempt to update a spa staff that doesn't exist
        SpaStaff updatedSpaStaff = new SpaStaff(999, "nonexistent_user", "new_password", "nonexistent@example.com", "Nonexistent User");
        try {
            spaStaffService.updateSpaStaff(999, updatedSpaStaff);
        } catch (SpaStaffServiceException e) {
            // Handle the exception if necessary
            assertEquals("Staff with ID 999 not found.", e.getMessage());
        }
    }

    @Test
    public void testDeleteSpaStaff() {
        // Add a spa staff
        SpaStaff spaStaff = new SpaStaff(7, "john_doe", "password", "john@example.com", "John Doe");
        spaStaffService.addSpaStaff(spaStaff);

        // Delete the spa staff
        spaStaffService.deleteSpaStaff(7);
    }

    @Test
    public void testDeleteNonexistentSpaStaff() {
        // Attempt to delete a nonexistent spa staff
        try {
            spaStaffService.deleteSpaStaff(999);
        } catch (SpaStaffServiceException e) {
            assertEquals("Staff with ID 999 not found.", e.getMessage());
        }
    }
}
