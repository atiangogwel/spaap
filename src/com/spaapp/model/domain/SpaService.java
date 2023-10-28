package com.spaapp.model.domain;

public class SpaService {
    private String serviceName;
    private String description;
    private double price;
    // Add other attributes as needed

    public SpaService(String serviceName, String description, double price) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
    }

    // Getters and setters for attributes

    @Override
    public String toString() {
        return "SpaService: " + serviceName;
    }
}
