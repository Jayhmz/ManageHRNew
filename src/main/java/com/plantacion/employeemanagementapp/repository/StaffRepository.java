package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmail(String email);
    @Query("SELECT s FROM Staff s ORDER BY s.id DESC LIMIT 10")
    List<Staff> findLast10Rows();
}
