package com.spaapp.model.services.spaservice;

import com.spaapp.model.domain.SpaService;

import java.util.ArrayList;
import java.util.List;

public class SpaServiceImpl implements ISpaService {
    private List<SpaService> spaServices;
    private int nextServiceId;

    public SpaServiceImpl() {
        spaServices = new ArrayList<>();
        nextServiceId = 1;
    }

    @Override
    public void createSpaService(String serviceName, String description, double price) {
        SpaService newService = new SpaService(nextServiceId, serviceName, description, price);
        spaServices.add(newService);
        nextServiceId++;
    }

    @Override
    public SpaService readSpaService(int serviceId) {
        for (SpaService service : spaServices) {
            if (service.getServiceId() == serviceId) {
                return service;
            }
        }
        return null; // Service not found
    }

    @Override
    public boolean updateSpaService(int serviceId, String newDescription, double newPrice) {
        SpaService service = readSpaService(serviceId);
        if (service != null) {
            service.setDescription(newDescription);
            service.setPrice(newPrice);
            return true; // Service updated successfully
        }
        return false; // Service not found
    }

    @Override
    public boolean deleteSpaService(int serviceId) {
        SpaService service = readSpaService(serviceId);
        if (service != null) {
            spaServices.remove(service);
            return true; // Service deleted successfully
        }
        return false; // Service not found
    }

    @Override
    public void listAllSpaServices() {
        for (SpaService service : spaServices) {
            System.out.println(service);
        }
    }
}
