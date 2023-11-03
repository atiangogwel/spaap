package com.spaapp.model.services.loginservice;

import com.spaapp.model.domain.Customer;

public class LoginServiceImpl implements ILoginService {
    /**
     * Validates if the customer is indeed registered in our system.
     */
    public boolean authenticateCustomer(String username, String password) {
        //authentication logic here
        System.out.println("Entering method LoginServiceImpl::authenticateCustomer");
        if (username.equals("user1") && password.equals("pass123")) {
            return true;
        }
        
        return false;
    }
}
