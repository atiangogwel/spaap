package com.spaapp.model.business.manager;

import com.spaapp.model.services.spaservice.ISpaService;
import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;
import com.spaapp.model.services.spaservice.ILoginService;
import com.spaapp.model.services.spaservice.ISpaStaffService;
import com.spaapp.model.services.spaservice.LoginServiceImpl;
import com.spaapp.model.services.spaservice.SpaServiceImpl;
import com.spaapp.model.business.exception.ServiceLoadException;
import com.spaapp.model.domain.Customer;
import com.spaapp.model.domain.SpaApComposite;
import com.spaapp.model.services.spaservice.exception.SpaServiceException;
import com.spaapp.model.services.spaservice.exception.LoginServiceException;
import com.spaapp.model.services.spaservice.exception.CustomerServiceException;
import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;


import com.spaapp.model.domain.Customer;
import com.spaapp.model.domain.SpaService;
import com.spaapp.model.domain.SpaStaff;
import com.spaapp.model.services.spaservice.exception.CustomerServiceException;
import com.spaapp.model.services.spaservice.exception.SpaStaffServiceException;

import java.util.List;

public class SpaServiceManager {

    private ISpaService spaService;
    private ISpaStaffService spaStaffService;
    private ICustomerService customerService;
    private ILoginService loginService;

    public SpaServiceManager(ISpaService spaService, ISpaStaffService spaStaffService, ICustomerService customerService, ILoginService loginService) {
        this.spaService = spaService;
        this.spaStaffService = spaStaffService;
        this.customerService = customerService;
        this.loginService = loginService;
    }

    // SpaService related methods

    public void createSpaService(String serviceName, String description, double price) {
        spaService.createSpaService(serviceName, description, price);
    }

    public SpaService readSpaService(int serviceId) {
        return spaService.readSpaService(serviceId);
    }

    public boolean updateSpaService(int serviceId, String newDescription, double newPrice) {
        return spaService.updateSpaService(serviceId, newDescription, newPrice);
    }

    public boolean deleteSpaService(int serviceId) {
        return spaService.deleteSpaService(serviceId);
    }

    public void listAllSpaServices() {
        spaService.listAllSpaServices();
    }

    // SpaStaff related methods

    public void addSpaStaff(SpaStaff spaStaff) {
        spaStaffService.addSpaStaff(spaStaff);
    }

    public SpaStaff getSpaStaff(String staffName) {
        return spaStaffService.getSpaStaff(staffName);
    }

    public void updateSpaStaff(String staffName, SpaStaff updatedStaff) {
        spaStaffService.updateSpaStaff(staffName, updatedStaff);
    }

    public void deleteSpaStaff(String staffName) {
        spaStaffService.deleteSpaStaff(staffName);
    }

    // Customer related methods

    public void addCustomer(Customer customer) {
        try {
            customerService.addCustomer(customer);
        } catch (CustomerServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer getCustomerByUsername(String username) {
        return customerService.getCustomerByUsername(username);
    }

    public void updateCustomer(String username, Customer updatedCustomer) {
        customerService.updateCustomer(username, updatedCustomer);
    }

    public void deleteCustomer(String username) {
        customerService.deleteCustomer(username);
    }

    // Login related method

    public boolean authenticateCustomer(String username, String password) {
        try {
            return loginService.authenticateCustomer(username, password);
        } catch (LoginServiceException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
