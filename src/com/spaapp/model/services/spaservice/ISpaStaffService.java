package com.spaapp.model.services.spaservice;
import com.spaapp.model.domain.SpaStaff;

public interface ISpaStaffService {
    void addSpaStaff(SpaStaff spaStaff);
    SpaStaff getSpaStaff(String staffName);
    void updateSpaStaff(String staffName, SpaStaff updatedStaff);
    void deleteSpaStaff(String staffName);
}
