package com.spaapp.model.services.loginservice;

import com.spaapp.model.domain.Customer;

public interface ILoginService {
    boolean authenticateCustomer(String username, String password);
}
