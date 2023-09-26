package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.service.StaffService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateUserServiceImpl implements UserDetailsService {
    private final StaffService staffService;

    public CreateUserServiceImpl(StaffService staffService) {
        this.staffService = staffService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffService.findStaffByEmail(username);
        //validate user
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : staff.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return new User(staff.getEmail(), staff.getPassword(), authorities);
    }
}
