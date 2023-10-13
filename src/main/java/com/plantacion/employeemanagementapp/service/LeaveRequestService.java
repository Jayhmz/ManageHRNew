package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.LeaveRequest;
import com.plantacion.employeemanagementapp.model.dto.LeaveRequestDTO;

import java.security.Principal;
import java.util.List;

public interface LeaveRequestService {
    boolean createLeave(LeaveRequestDTO leaveRequestDTO, Principal principal);
    List<LeaveRequest> findAll();
    long getCountOfLeaveRequests();
    List<LeaveRequest> findByStaffId(Long staffId);
}
