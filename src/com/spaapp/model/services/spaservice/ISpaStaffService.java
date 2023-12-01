package com.spaapp.model.services.spaservice;
import java.util.List;

import com.spaapp.model.domain.SpaStaff;
import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;

public interface ISpaStaffService {
    void addSpaStaff(SpaStaff spaStaff);
    void updateSpaStaff(int staffId, SpaStaff updatedStaff);
    List<SpaStaff> getAllSpaStaff() throws SpaStaffServiceException;
	void deleteSpaStaff(int staff_id);
}
