package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.enums.RoleType;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByRoleType(RoleType roleType);
}
