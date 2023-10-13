package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.dto.TimeDTO;

import java.security.Principal;

public interface TimeRecordService {
    boolean signInTime(String staffEmail);
    boolean signOutTime(String staffEmail);
    double calcHoursWorked(Long staffId);

}
