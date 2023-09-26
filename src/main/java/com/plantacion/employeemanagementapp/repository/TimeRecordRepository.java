package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
}
