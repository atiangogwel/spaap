package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.domain.SpaStaff;

public class SpaStaffServiceImplTest {

	 private SpaStaffServiceImpl spaStaffService;

	    @Before
	    public void setUp() {
	        spaStaffService = new SpaStaffServiceImpl();
	    }

	    @Test
	    public void testAddAndGetSpaStaff() {
	        SpaStaff staff = new SpaStaff("John Doe", "Massage Therapist");
	        spaStaffService.addSpaStaff(staff);

	        SpaStaff readStaff = spaStaffService.getSpaStaff("John Doe");
	        assertEquals(staff, readStaff);
	    }

	    @Test
	    public void testUpdateSpaStaff() {
	        SpaStaff staff = new SpaStaff("John Doe", "Massage Therapist");
	        spaStaffService.addSpaStaff(staff);

	        SpaStaff updatedStaff = new SpaStaff("John Doe", "Senior Massage Therapist");
	        spaStaffService.updateSpaStaff("John Doe", updatedStaff);

	        SpaStaff readStaff = spaStaffService.getSpaStaff("John Doe");
	        assertEquals(updatedStaff, readStaff);
	    }

	    @Test
	    public void testDeleteSpaStaff() {
	        SpaStaff staff = new SpaStaff("John Doe", "Massage Therapist");
	        spaStaffService.addSpaStaff(staff);

	        spaStaffService.deleteSpaStaff("John Doe");

	        SpaStaff readStaff = spaStaffService.getSpaStaff("John Doe");
	        assertNull(readStaff);
	    }

}
