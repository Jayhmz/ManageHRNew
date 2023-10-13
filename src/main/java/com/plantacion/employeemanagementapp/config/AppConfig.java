package com.plantacion.employeemanagementapp.config;

import com.plantacion.employeemanagementapp.eventListener.CustomSessionListener;
import com.plantacion.employeemanagementapp.service.StaffService;
import com.plantacion.employeemanagementapp.service.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class AppConfig {
    @Autowired
    private StaffService staffService;
    @Autowired
    private TimeRecordService timeRecordService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public static HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public ServletListenerRegistrationBean<CustomSessionListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<CustomSessionListener> bean =
                new ServletListenerRegistrationBean<>(new CustomSessionListener(staffService, timeRecordService));
        return bean;
    }
}
