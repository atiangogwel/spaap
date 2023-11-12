package com.spaapp.model.services.loginservice;

import com.spaapp.model.domain.Customer;
import com.spaapp.model.services.spaservice.exception.LoginServiceException;

public class LoginServiceImpl implements ILoginService {
    /**
     * Validates if the customer is indeed registered in our system.
     */
    public boolean authenticateCustomer(String username, String password) {
        //authentication logic here
        System.out.println("Entering method LoginServiceImpl::authenticateCustomer");

        if ("user1".equals(username) && "pass123".equals(password)) {
            return true;
        } else {
            throw new LoginServiceException("Authentication failed for user: " + username);
        }
    }
}
