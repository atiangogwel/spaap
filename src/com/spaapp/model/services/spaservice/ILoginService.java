package com.spaapp.model.services.spaservice;

import com.spaapp.model.domain.Customer;

public interface ILoginService {
    boolean authenticateCustomer(String username, String password);
}
