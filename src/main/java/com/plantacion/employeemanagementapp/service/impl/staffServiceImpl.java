package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.exception.CreateStaffFailedException;
import com.plantacion.employeemanagementapp.exception.PasswordMismatchException;
import com.plantacion.employeemanagementapp.exception.RoleNotFoundException;
import com.plantacion.employeemanagementapp.exception.StaffNotFoundException;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.dto.RoleDTO;
import com.plantacion.employeemanagementapp.model.dto.StaffDTO;
import com.plantacion.employeemanagementapp.model.embeddable.Address;
import com.plantacion.employeemanagementapp.model.embeddable.Phone;
import com.plantacion.employeemanagementapp.model.enums.RoleType;
import com.plantacion.employeemanagementapp.repository.StaffRepository;
import com.plantacion.employeemanagementapp.service.RoleService;
import com.plantacion.employeemanagementapp.service.StaffService;
import com.plantacion.employeemanagementapp.service.TimeRecordService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class staffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final TimeRecordService timeRecordService;

    public staffServiceImpl(StaffRepository staffRepository, RoleService roleService, PasswordEncoder passwordEncoder, TimeRecordService timeRecordService) {
        this.staffRepository = staffRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.timeRecordService = timeRecordService;
    }

    @Override
    @Transactional
    public boolean createStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setFirstname(staffDTO.getFirstname());
        staff.setLastname(staffDTO.getLastname());
        staff.setEmail(staffDTO.getEmail());
        staff.setPrimaryHomeAddress(getAddress(staffDTO));
        staff.setMobile(new Phone(staffDTO.getPhone().getNumber(), staffDTO.getPhone().getCountryCode()));
        staff.setRoles(getRoles(staffDTO));
        if (staffDTO.getPassword().trim().equals(staffDTO.getConfirmPassword().trim())) {
            staff.setPassword(passwordEncoder.encode(staffDTO.getPassword().trim()));
        } else {
            throw new PasswordMismatchException("password does not match");
        }
        try {
            staffRepository.save(staff);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            throw new CreateStaffFailedException("Unable to create staff with the provided details");
//            return false;
        }
    }

    @Override
    public Staff findStaffByEmail(String email) {
        try {
            System.out.println("found user by email");
            return staffRepository.findByEmail(email);
        } catch (Exception e) {
            System.out.println("didnt find any user by email");
            throw new StaffNotFoundException("Invalid staff details");
        }
    }
    @Override
    public boolean adminCreateStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setFirstname(staffDTO.getFirstname());
        staff.setLastname(staffDTO.getLastname());
        staff.setEmail(staffDTO.getEmail());
        staff.setPrimaryHomeAddress(getAddress(staffDTO));
        staff.setMobile(new Phone(staffDTO.getPhone().getNumber(), staffDTO.getPhone().getCountryCode()));
        staff.setRoles(getRoles(staffDTO));
        staff.setPassword(passwordEncoder.encode("Welcome@123"));
        try {
            staffRepository.save(staff);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            throw new CreateStaffFailedException("Unable to create staff with the provided details");
//            return false;
        }
    }

    @Override
    public List<Staff> adminGetAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> adminGetRecentTenStaff() {
        return staffRepository.findLast10Rows();
    }

    @Override
    public int registeredStaffCount() {
        return (int) staffRepository.count();
    }

    @Override
    public Page getPaginatedStaffs(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    private Set<Role> getRoles(StaffDTO staffDTO) {
        Set<Role> roles = new HashSet<>();
        for (RoleDTO roleDTO : staffDTO.getRoles()) {
            try {
                Role role = roleService.findByRoleType(RoleType.valueOf(roleDTO.getRoleType().trim()));
                roles.add(role);
            } catch (Exception e) {
                throw new RoleNotFoundException("Selected role is not in record");
            }
        }
        return roles;
    }

    private Address getAddress(StaffDTO staffDTO) {
        Address address = new Address(staffDTO.getAddress().getZipCode(),
                staffDTO.getAddress().getStreet(),
                staffDTO.getAddress().getCity(),
                staffDTO.getAddress().getCountry());
        return address;
    }
}
