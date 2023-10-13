package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.model.domain.LeaveRequest;
import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.dto.LeaveRequestDTO;
import com.plantacion.employeemanagementapp.repository.LeaveRequestRepository;
import com.plantacion.employeemanagementapp.repository.StaffRepository;
import com.plantacion.employeemanagementapp.service.LeaveRequestService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final StaffRepository staffRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository, StaffRepository staffRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public boolean createLeave(LeaveRequestDTO leaveRequestDTO, Principal principal) {
        Staff staff = staffRepository.findByEmail(principal.getName());
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setLeaveBody(leaveRequestDTO.getLeaveBody());
        leaveRequest.setLeaveSubject(leaveRequestDTO.getLeaveSubject());
        leaveRequest.setStaff(staff);
        try{
            leaveRequestRepository.save(leaveRequest);
            return true;
        }catch (Exception e){
            throw new UsernameNotFoundException("Staff not found, Kindly log in with correct details");
        }
    }

    @Override
    public List<LeaveRequest> findAll() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public long getCountOfLeaveRequests() {
        return leaveRequestRepository.count();
    }

    @Override
    public List<LeaveRequest> findByStaffId(Long staffId) {
        return leaveRequestRepository.findByStaffId(staffId);
    }
}
