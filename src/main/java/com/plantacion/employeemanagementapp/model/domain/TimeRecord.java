package com.plantacion.employeemanagementapp.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "time_record")
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_in", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeIn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_out", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }
}
