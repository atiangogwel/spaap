package com.spaapp.model.domain;

public class ServiceBooking {
    private int bookingId;
    private Appointment appointment;
    private SpaService spaService;
    // Add other attributes as needed

    public ServiceBooking(int bookingId, Appointment appointment, SpaService spaService) {
        this.bookingId = bookingId;
        this.appointment = appointment;
        this.spaService = spaService;
    }

    // Getters and setters for attributes

    @Override
    public String toString() {
        return "ServiceBooking: ID-" + bookingId;
    }
}
