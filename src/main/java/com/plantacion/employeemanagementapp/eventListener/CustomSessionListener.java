package com.plantacion.employeemanagementapp.eventListener;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.service.StaffService;
import com.plantacion.employeemanagementapp.service.TimeRecordService;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class CustomSessionListener implements HttpSessionListener {


    private final StaffService staffService;

    private final TimeRecordService timeRecordService;

    public CustomSessionListener(StaffService staffService, TimeRecordService timeRecordService){
        this.staffService = staffService;
        this.timeRecordService = timeRecordService;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("AppSession");
        System.out.println("in the session destroyed method begins "+username);
        if (username != null){
            Staff staff = staffService.findStaffByEmail(username);
            timeRecordService.signOutTime(staff.getEmail());
            System.out.println("in the session destroyed method end " +username);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }
}
