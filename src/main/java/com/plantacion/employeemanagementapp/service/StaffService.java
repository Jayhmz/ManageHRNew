package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.dto.StaffDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    boolean createStaff(StaffDTO staffDTO);
    Staff findStaffByEmail(String email);
    boolean adminCreateStaff(StaffDTO staffDTO);
    List<Staff> adminGetAllStaff();
    int registeredStaffCount();
    int leaveRequestCount();

    Page getPaginatedStaffs(Pageable pageable);


}
