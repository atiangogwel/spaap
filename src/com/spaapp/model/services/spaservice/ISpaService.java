package com.spaapp.model.services.spaservice;

import com.spaapp.model.domain.SpaService;

public interface ISpaService {
    void createSpaService(String serviceName, String description, double price);
    SpaService readSpaService(int serviceId);
    boolean updateSpaService(int serviceId, String newDescription, double newPrice);
    boolean deleteSpaService(int serviceId);
    void listAllSpaServices();
}
