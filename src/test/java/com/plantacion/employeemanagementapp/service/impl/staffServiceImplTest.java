package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.exception.PasswordMismatchException;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.dto.AddressDTO;
import com.plantacion.employeemanagementapp.model.dto.PhoneDTO;
import com.plantacion.employeemanagementapp.model.dto.RoleDTO;
import com.plantacion.employeemanagementapp.model.dto.StaffDTO;
import com.plantacion.employeemanagementapp.model.enums.RoleType;
import com.plantacion.employeemanagementapp.repository.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class staffServiceImplTest {

    @InjectMocks
    private staffServiceImpl service;

    @Mock
    private StaffRepository repository;
    @Mock
    private PasswordEncoder encoder;

    private StaffDTO staffDTO;
    private AddressDTO addressDTO;
    private PhoneDTO phoneDTO;
    private RoleDTO roleDTO;
    private Set<RoleDTO> role = new HashSet<>();

    @BeforeEach
    void setUp() {
        phoneDTO = new PhoneDTO("08132086252", "+234");
        addressDTO = new AddressDTO("110112", "tejuosho", "Surulere", "Nigeria");
        roleDTO = new RoleDTO("SUPER_ADMIN");
        role.add(roleDTO);
        staffDTO = new StaffDTO("James", "Damilare",
                "test@mail.com", addressDTO, phoneDTO,
                "12345", "12345", role);
    }

    @Test
    @DisplayName("test for password mismatch")
    void confirmPaswordMatch() {
        assertTrue(staffDTO.getPassword().equals(staffDTO.getConfirmPassword()));
    }

    @Test
    @DisplayName("test for exception throwing on password mismatch")
    void throwPasswordMismatchExceptionOnPasswordMismatch() {
        assertThrows(PasswordMismatchException.class, () -> service.createStaff(staffDTO));
        verify(repository, never()).save(any(Staff.class));
    }

    @Test
    void createStaff() {
        Set<Role> roles = new HashSet<>();
        for (RoleDTO role : staffDTO.getRoles())
            roles.add(new Role(RoleType.valueOf(role.getRoleType())));

        Staff staff = new Staff();
        staff.setPassword(staffDTO.getPassword());
        staff.setFirstname(staffDTO.getFirstname());
        staff.setLastname(staffDTO.getLastname());
        staff.setEmail(staffDTO.getEmail());
        staff.setRoles(roles);

        when(repository.save(any(Staff.class))).thenAnswer(invocation -> invocation.getArgument(0));
        assertTrue(service.createStaff(staffDTO));
        verify(repository, atMost(1)).save(staff);
    }
}