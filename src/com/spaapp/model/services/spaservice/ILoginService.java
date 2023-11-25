package com.spaapp.model.services.spaservice;

import com.spaapp.model.services.spaservice.LoginServiceImpl.AuthResult;
import com.spaapp.model.services.spaservice.exception.LoginServiceException;

import java.sql.SQLException;

public interface ILoginService {
	AuthResult authenticateCustomer(String username, String password) throws LoginServiceException;
}
