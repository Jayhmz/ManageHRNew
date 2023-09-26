package com.plantacion.employeemanagementapp.model.domain;

import com.plantacion.employeemanagementapp.model.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public Role() {
    }

    public Role(RoleType name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}
