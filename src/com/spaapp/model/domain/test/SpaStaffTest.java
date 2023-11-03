package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpaStaffTest {

	@Test
	public void test() {
        SpaStaff spaStaff = new SpaStaff();
        String staffName = "John Doe";

        // Set the name using the setter
        spaStaff.setName(staffName);

        // Use the getter to retrieve the name
        String retrievedName = spaStaff.getName();

        // Validate that the name is not null
        assertNotNull(retrievedName);

        // Validate that the retrieved name matches the expected name
        assertEquals(staffName, retrievedName);

	}

}
