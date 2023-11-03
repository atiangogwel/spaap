package com.spaapp.model.services.factory;
import com.spaapp.model.services.loginservice.ILoginService;
import com.spaapp.model.services.loginservice.LoginServiceImpl;

public class ServiceFactory {

    public ILoginService createLoginService() {
        return new LoginServiceImpl();
    }

}