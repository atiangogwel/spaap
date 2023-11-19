// SpaStaffServiceImpl.java

package com.spaapp.model.services.spaservice;
import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;

import com.spaapp.model.domain.SpaStaff;

import java.util.HashMap;
import java.util.Map;

public class SpaStaffServiceImpl implements ISpaStaffService {
    private Map<String, SpaStaff> spaStaffMap;

    public SpaStaffServiceImpl() {
        spaStaffMap = new HashMap<>();
    }

    @Override
    public void addSpaStaff(SpaStaff spaStaff) {
        if (spaStaffMap.containsKey(spaStaff.getStaffName())) {
            throw new SpaStaffServiceException("Spa staff with name " + spaStaff.getStaffName() + " already exists.");
        }
        spaStaffMap.put(spaStaff.getStaffName(), spaStaff);
    }

    @Override
    public SpaStaff getSpaStaff(String staffName) {
        SpaStaff spaStaff = spaStaffMap.get(staffName);
        if (spaStaff == null) {
            throw new SpaStaffServiceException("Spa staff with name " + staffName + " not found.");
        }
        return spaStaff;
    }

    @Override
    public void updateSpaStaff(String staffName, SpaStaff updatedStaff) {
        if (spaStaffMap.containsKey(staffName)) {
            spaStaffMap.put(staffName, updatedStaff);
        } else {
            throw new SpaStaffServiceException("Spa staff with name " + staffName + " not found.");
        }
    }

    @Override
    public void deleteSpaStaff(String staffName) {
        if (spaStaffMap.containsKey(staffName)) {
            spaStaffMap.remove(staffName);
        } else {
            throw new SpaStaffServiceException("Spa staff with name " + staffName + " not found.");
        }
    }
}
