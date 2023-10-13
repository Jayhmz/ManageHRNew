package com.plantacion.employeemanagementapp.config;

import com.plantacion.employeemanagementapp.service.impl.CreateUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@EnableWebSecurity
@Configuration
public class AppSecurityConfiguration {

    private final CreateUserServiceImpl service;

    private String[] WHITELIST = {"/auth/register", "/auth/registration"};
    private String[] TIME_WHITELIST = {"/app/time-in", "/app/time-out"};

    public AppSecurityConfiguration(CreateUserServiceImpl service) {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((request) ->
                request.dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("SUPER_ADMIN")
                        .requestMatchers("/app/home").hasAnyAuthority("STAFF")
                        .requestMatchers(TIME_WHITELIST).hasAnyAuthority("STAFF", "SUPER_ADMIN")
                        .anyRequest().authenticated()
        );
        http.userDetailsService(service);
        http.formLogin(form ->
                form.loginPage("/auth/login").permitAll()
                        .defaultSuccessUrl("/app")
                        .loginProcessingUrl("/login")
                        .failureUrl("/auth/error")
        );
        http.logout(l ->
                l.invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/auth/login").permitAll());
        return http.build();
    }


}
