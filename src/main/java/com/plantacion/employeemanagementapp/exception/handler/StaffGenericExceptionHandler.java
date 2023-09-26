package com.plantacion.employeemanagementapp.exception.handler;

import com.plantacion.employeemanagementapp.exception.CreateStaffFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StaffGenericExceptionHandler {
    @ExceptionHandler(CreateStaffFailedException.class)
    ResponseEntity<String> createStaffFailed(CreateStaffFailedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
