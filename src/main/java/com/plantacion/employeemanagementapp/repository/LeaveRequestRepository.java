package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}
