package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.spaapp.persistence.DatabaseInitializer;
import com.spaapp.model.domain.SpaService;
import com.spaapp.model.services.spaservice.SpaServiceImpl;
import com.spaapp.model.services.spaservice.ISpaService;

public class SpaServiceTest {
    private ISpaService spaService;

    @Before
    public void setUp() {
        try {
            // Initialize the testing database before each test
            DatabaseInitializer.initializeDatabase();
            spaService = new SpaServiceImpl();
        } catch (Exception e) {
            // Handle exceptions (log or rethrow as a RuntimeException)
            e.printStackTrace();
            throw new RuntimeException("Error initializing database for testing.", e);
        }
    }

    @Test
    public void testCreateAndGetSpaService() {
        // Create a new spa service
        spaService.createSpaService("Massage", "Full body massage", 50.0);

        // Get the created spa service
        SpaService retrievedService = spaService.readSpaService(1); // Assuming the service_id is 1
        assertNotNull(retrievedService);
        assertEquals("Massage", retrievedService.getServiceName());
        assertEquals("Full body massage", retrievedService.getDescription());
        assertEquals(50.0, retrievedService.getPrice(), 0.01);
    }

    @Test
    public void testUpdateSpaService() {
        // Create a new spa service
        spaService.createSpaService("Facial", "Deep cleansing facial", 80.0);

        // Update the spa service
        assertTrue(spaService.updateSpaService(1, "New description", 90.0)); // Assuming the service_id is 1

        // Get the updated spa service
        SpaService updatedService = spaService.readSpaService(1); // Assuming the service_id is 1
        assertNotNull(updatedService);
        assertEquals("Facial", updatedService.getServiceName());
        assertEquals("New description", updatedService.getDescription());
        assertEquals(90.0, updatedService.getPrice(), 0.01);
    }

    @Test
    public void testUpdateNonexistentSpaService() {
        // Attempt to update a nonexistent spa service
        assertFalse(spaService.updateSpaService(999, "New description", 90.0)); // Assuming the service_id doesn't exist
    }

    @Test
    public void testDeleteSpaService() {
        // Create a new spa service
        spaService.createSpaService("Sauna", "Relaxing sauna session", 20.0);

        // Delete the spa service
        assertTrue(spaService.deleteSpaService(1)); // Assuming the service_id is 1

        // Get the deleted spa service
        SpaService deletedService = spaService.readSpaService(1); // Assuming the service_id is 1
        assertNull(deletedService);
    }

    @Test
    public void testDeleteNonexistentSpaService() {
        // Attempt to delete a nonexistent spa service
        assertFalse(spaService.deleteSpaService(999)); // Assuming the service_id doesn't exist
    }

    @Test
    public void testListAllSpaServices() {
        // Create multiple spa services
        spaService.createSpaService("Aromatherapy", "Scented oil massage", 60.0);
        spaService.createSpaService("Pedicure", "Foot treatment", 30.0);
        spaService.createSpaService("Body Scrub", "Exfoliating body scrub", 40.0);

        // List all spa services and check the output
        spaService.listAllSpaServices();
    }
}
