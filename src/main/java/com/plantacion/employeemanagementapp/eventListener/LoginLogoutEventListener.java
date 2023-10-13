package com.plantacion.employeemanagementapp.eventListener;

import com.plantacion.employeemanagementapp.service.impl.LoginLogOutEventServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginLogoutEventListener {
    @Autowired
    private LoginLogOutEventServiceImpl logInLogOutEventService;

    @EventListener
    public void onLoginSuccess(AuthenticationSuccessEvent event){
        System.out.println("onLogInSuccessEvent service called and triggered");
        logInLogOutEventService.logLoginTime(event.getAuthentication().getName());
    }

    @EventListener
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        logInLogOutEventService.logLogoutTime(event.getAuthentication().getName());
        System.out.println("onLogoutSuccessEvent service called and triggered");
    }
}
