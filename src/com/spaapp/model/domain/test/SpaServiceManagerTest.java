package com.spaapp.model.domain.test;
import com.spaapp.model.domain.SpaService;
import com.spaapp.model.services.spaservice.SpaServiceManager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
public class SpaServiceManagerTest {
    private SpaServiceManager spaServiceManager;

    @Before
    public void setUp() {
        spaServiceManager = new SpaServiceManager();
    }

    @Test
    public void testCreateSpaService() {
        spaServiceManager.createSpaService("Service1", "Description1", 50.0);
        SpaService service = spaServiceManager.readSpaService(1);
        assertNotNull(service);
        assertEquals("Service1", service.getServiceName());
    }

    @Test
    public void testUpdateSpaService() {
        spaServiceManager.createSpaService("Service2", "Description2", 75.0);
        boolean updated = spaServiceManager.updateSpaService(1, "New Description", 80.0);
        assertTrue(updated);

        SpaService service = spaServiceManager.readSpaService(1);
        assertNotNull(service);
        assertEquals("New Description", service.getDescription());
        assertEquals(80.0, service.getPrice(), 0.001); // Specify a delta for double comparison
    }

    @Test
    public void testDeleteSpaService() {
        spaServiceManager.createSpaService("Service3", "Description3", 60.0);
        boolean deleted = spaServiceManager.deleteSpaService(1);
        assertTrue(deleted);

        SpaService service = spaServiceManager.readSpaService(1);
        assertNull(service);
    }
}
