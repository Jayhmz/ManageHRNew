package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.enums.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/app")
public class AppController {

    @GetMapping
    public String homepage(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("SUPER_ADMIN"))) {
            return "redirect:/admin";
        } else {
            System.out.println(authentication.getAuthorities());
            return "redirect:/app/home";
        }
    }


    @GetMapping("/home")
    public String showHomepage(){
        return "homepage";
    }
}
