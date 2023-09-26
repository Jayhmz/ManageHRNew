package com.plantacion.employeemanagementapp.model.dto;

public class RoleDTO {
    private String roleType;

    public RoleDTO(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleType='" + roleType + '\'' +
                '}';
    }
}
