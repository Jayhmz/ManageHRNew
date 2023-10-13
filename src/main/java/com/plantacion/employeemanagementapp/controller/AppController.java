package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.service.TimeRecordService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/app")
public class AppController {

    private final TimeRecordService timeRecordService;

    public AppController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

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

    @GetMapping("/time-in")
    @ResponseBody
    public String processTimeIn(Principal principal){
        timeRecordService.signInTime(principal.getName());
        return "staff resumes";
    }
    @GetMapping("/time-out")
    @ResponseBody
    public String processTimeOut(Principal principal){
        timeRecordService.signOutTime(principal.getName());
        return "staff takes a break";
    }
}
