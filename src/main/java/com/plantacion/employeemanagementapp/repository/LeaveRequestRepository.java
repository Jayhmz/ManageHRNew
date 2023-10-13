package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    @Query("SELECT lr FROM LeaveRequest lr where lr.staff.id = :id")
    List<LeaveRequest> findByStaffId(Long id);

}
