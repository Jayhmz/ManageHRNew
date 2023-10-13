package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TimeDTO {
    @NotNull(message = "Field cannot be empty")
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
