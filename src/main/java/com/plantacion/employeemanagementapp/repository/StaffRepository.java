package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmail(String email);
}
