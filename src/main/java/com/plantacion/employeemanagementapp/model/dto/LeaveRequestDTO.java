package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LeaveRequestDTO {
    @NotNull(message = "Subject cannot be empty")
    private String leaveSubject;
    @NotNull(message = "Body cannot be empty")
    private String leaveBody;

    public String getLeaveSubject() {
        return leaveSubject;
    }

    public void setLeaveSubject(String leaveSubject) {
        this.leaveSubject = leaveSubject;
    }

    public String getLeaveBody() {
        return leaveBody;
    }

    public void setLeaveBody(String leaveBody) {
        this.leaveBody = leaveBody;
    }
}
