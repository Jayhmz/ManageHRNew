package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.service.TimeRecordService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LoginLogOutEventServiceImpl {
    private final TimeRecordService timeRecordService;

    public LoginLogOutEventServiceImpl(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }
    public void logLogoutTime(String staffEmail) {
        timeRecordService.signOutTime(staffEmail);
    }
    public void logLoginTime(String staffEmail) {
        timeRecordService.signInTime(staffEmail);
    }
}
