package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.dto.StaffDTO;
import com.plantacion.employeemanagementapp.model.enums.RoleType;
import com.plantacion.employeemanagementapp.service.RoleService;
import com.plantacion.employeemanagementapp.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final StaffService staffService;
    private final RoleService roleService;

    public AuthenticationController(StaffService staffService, RoleService roleService) {
        this.staffService = staffService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(@ModelAttribute("staffDTO") StaffDTO staffDTO,
                                       Model model) {
        model.addAttribute("staff_roles", roleService.getAllRoles());
        return "register";
    }

    @PostMapping("/registration")
    public String processRegistration(@Valid @ModelAttribute("staffDTO") StaffDTO staffDTO,
                                      Model model, BindingResult result){
        System.out.println(staffDTO);
        if(result.hasErrors()){
            for (ObjectError objectErrors : result.getAllErrors()){
                System.out.println(objectErrors.toString());
            }
            return "redirect:/register?error";
        }
        staffService.createStaff(staffDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "loginpage";
    }
}
