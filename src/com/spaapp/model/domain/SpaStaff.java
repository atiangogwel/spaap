package com.spaapp.model.domain;

public class SpaStaff {
    private String staffName;
    private String staffRole;

    public SpaStaff(String staffName, String staffRole) {
        this.staffName = staffName;
        this.staffRole = staffRole;
    }


    @Override
    public String toString() {
        return "SpaStaff: " + staffName;
    }
}
