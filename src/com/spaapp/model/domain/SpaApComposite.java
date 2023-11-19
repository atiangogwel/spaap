package com.spaapp.model.domain;

import java.io.Serializable;

public class SpaApComposite implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Holds information of the customer */
    private Customer customer;

    /** Holds the list of spa services available for booking */
    private SpaService availableServices;

    /** Customer's spa appointment itinerary */
    private Itinerary itinerary;

    /** Spa service chosen by the customer to book */
    private SpaService bookedService;

    public SpaApComposite() {
        // Default constructor
    }

    // Getters and setters for attributes

    @Override
    public String toString() {
        StringBuilder strBfr = new StringBuilder();
        strBfr.append("\nCustomer Info:\n");
        strBfr.append(customer);
        strBfr.append("\n\nAvailable Spa Services:\n");
        strBfr.append(availableServices);
        strBfr.append("\n\nSpa Appointment Itinerary:\n");
        strBfr.append(itinerary);
        strBfr.append("\n\nBooked Spa Service:\n");
        strBfr.append(bookedService);

        return strBfr.toString();
    }
}
