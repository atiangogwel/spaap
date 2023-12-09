package com.spaapp.model.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Itinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String spaAppointmentCityId;
    private String spaAppointmentCity;
    private String appointmentMonth;
    private String appointmentDay;
    private String appointmentYear;
    private String appointmentTime;
    private int qtyAppointmentDays = 0;

    public Itinerary() {
        // Default constructor
    }

    public Itinerary(String spaAppointmentCity,
                     String appointmentMonth,
                     String appointmentDay,
                     String appointmentYear,
                     String appointmentTime) {
        this.spaAppointmentCity = spaAppointmentCity;
        this.appointmentMonth = appointmentMonth;
        this.appointmentDay = appointmentDay;
        this.appointmentYear = appointmentYear;
        this.appointmentTime = appointmentTime;

        // Determine the quantity of appointment days using Calendar class.
        Calendar appointmentCalendar = Calendar.getInstance();
        appointmentCalendar.set(Integer.parseInt(appointmentYear),
                (Integer.parseInt(appointmentMonth) - 1),
                Integer.parseInt(appointmentDay));

        // Assume a default appointment duration for simplicity
        int defaultAppointmentDurationInDays = 1;

        // Calculate the end date by adding the default duration
        Calendar endCalendar = (Calendar) appointmentCalendar.clone();
        endCalendar.add(Calendar.DAY_OF_MONTH, defaultAppointmentDurationInDays);

        // Calculate the quantity of appointment days
        qtyAppointmentDays = defaultAppointmentDurationInDays;
    }

    // Getters and setters for attributes

    @Override
    public String toString() {
        return "SpaAppointmentCity Id: " + spaAppointmentCityId +
                "\nSpaAppointmentCity: " + spaAppointmentCity +
                "\nAppointmentMonth: " + appointmentMonth +
                "\nAppointmentDay: " + appointmentDay +
                "\nAppointmentYear: " + appointmentYear +
                "\nAppointmentTime: " + appointmentTime +
                "\nQtyAppointmentDays: " + qtyAppointmentDays;
    }
}
