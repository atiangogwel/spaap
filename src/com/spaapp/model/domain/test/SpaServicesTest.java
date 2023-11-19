package com.spaapp.model.domain.test;
import  com.spaapp.model.services.spaservice.SpaServiceImpl;
import com.spaapp.model.services.spaservice.ISpaService;
import com.spaapp.model.domain.SpaService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Before;

public class SpaServicesTest {
    private ISpaService spaService;

    @Before
    public void setUp() {
        spaService = new SpaServiceImpl();
    }

    @Test
    public void testCreateSpaService() {
        spaService.createSpaService("Massage", "Relaxing Massage", 50.0);
        assertEquals(1, spaService.readSpaService(1).getServiceId());
    }

    @Test
    public void testReadSpaService() {
        spaService.createSpaService("Facial", "Deep Cleansing Facial", 75.0);
        SpaService readService = spaService.readSpaService(1);
        assertNotNull(readService);
        assertEquals("Facial", readService.getServiceName());
    }

    @Test
    public void testUpdateSpaService() {
        spaService.createSpaService("Sauna", "Infrared Sauna Session", 30.0);
        assertTrue(spaService.updateSpaService(1, "Steam Room", 25.0));
        assertEquals("Steam Room", spaService.readSpaService(1).getDescription());
    }

    @Test
    public void testDeleteSpaService() {
        spaService.createSpaService("Manicure", "Classic Manicure", 20.0);
        assertTrue(spaService.deleteSpaService(1));
        assertNull(spaService.readSpaService(1));
    }

    @Test
    public void testListAllSpaServices() {
        spaService.createSpaService("Pedicure", "Spa Pedicure", 25.0);
        spaService.createSpaService("Body Wrap", "Detox Body Wrap", 60.0);

        // Redirect System.out.println to capture printed output
        final StringBuilder printedOutput = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                printedOutput.append((char) b);
            }
        }));

        spaService.listAllSpaServices();

        // Reset System.out back to the console
        System.setOut(System.out);

        // Verify the printed output
        assertTrue(printedOutput.toString().contains("Pedicure"));
        assertTrue(printedOutput.toString().contains("Body Wrap"));
    }
}