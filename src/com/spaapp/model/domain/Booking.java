package com.spaapp.model.domain;

public class Booking {
    private int id;
    private String customerName;
    private String serviceName;
    private String date;

    public Booking(int id, String customerName, String serviceName, String date) {
        this.id = id;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.date = date;
    }
}
