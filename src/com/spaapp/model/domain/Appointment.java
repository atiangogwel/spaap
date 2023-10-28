package com.spaapp.model.domain;

import java.util.Date;

public class Appointment {
    private Date appointmentDate;
    private SpaService spaService;
    private Customer customer;

    public Appointment(Date appointmentDate, SpaService spaService, Customer customer) {
        this.appointmentDate = appointmentDate;
        this.spaService = spaService;
        this.customer = customer;
    }

    // Getters and setters for attributes

    @Override
    public String toString() {
        return "Appointment: Date-" + appointmentDate;
    }
}
