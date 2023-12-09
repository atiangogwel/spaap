package com.spaapp.model.business.manager;

import com.spaapp.model.services.spaservice.ISpaService;
import com.spaapp.model.services.spaservice.SpaStaffServiceImpl;
import com.spaapp.model.services.spaservice.CustomerServiceImpl;
import com.spaapp.model.services.spaservice.ICustomerService;
import com.spaapp.model.services.spaservice.ILoginService;
import com.spaapp.model.services.spaservice.ISpaStaffService;
import com.spaapp.model.services.spaservice.LoginServiceImpl;
import com.spaapp.model.services.spaservice.LoginServiceImpl.AuthResult;
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

public class SpaServiceManager implements ISpaService {

    private ISpaService spaService;
    private ISpaStaffService spaStaffService;
    private ICustomerService customerService;
    private ILoginService loginService;
    // Default constructor
    public SpaServiceManager() {
        this.spaService = new SpaServiceImpl();
        this.spaStaffService = new SpaStaffServiceImpl();
        this.customerService = new CustomerServiceImpl();
        this.loginService = new LoginServiceImpl();
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

    public List<SpaStaff> getAllSpaStaff() {
        return spaStaffService.getAllSpaStaff();
    }

    public void updateSpaStaff(int staffId, SpaStaff updatedStaff) {
        spaStaffService.updateSpaStaff(staffId, updatedStaff);
    }

    public void deleteSpaStaff(int staffId) {
        spaStaffService.deleteSpaStaff(staffId);
    }

    // Customer related methods

    public void addCustomer(Customer customer) {
        try {
            customerService.addCustomer(customer);
        } catch (CustomerServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer(int username, Customer updatedCustomer) {
        customerService.updateCustomer(username, updatedCustomer);
    }

    public void deleteCustomer(int username) {
        customerService.deleteCustomer(username);
    }

    // Login related method

    public AuthResult authenticateCustomer(String username, String password) {
        try {
            return loginService.authenticateCustomer(username, password);
        } catch (LoginServiceException e) {
            System.out.println(e.getMessage());
            return new AuthResult(false, null);
        }
    }
}
