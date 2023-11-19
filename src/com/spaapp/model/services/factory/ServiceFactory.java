package com.spaapp.model.services.factory;
import com.spaapp.model.services.spaservice.ILoginService;
import com.spaapp.model.services.spaservice.LoginServiceImpl;

public class ServiceFactory {

    public ILoginService createLoginService() {
        return new LoginServiceImpl();
    }

}