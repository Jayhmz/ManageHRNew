package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.enums.RoleType;
import com.plantacion.employeemanagementapp.repository.RoleRepository;
import com.plantacion.employeemanagementapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public Role findByRoleType(RoleType roleType) {
        return repository.findByName(roleType);
    }

}
