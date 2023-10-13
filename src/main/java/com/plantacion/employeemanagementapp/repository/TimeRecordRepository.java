package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.domain.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
    @Query("SELECT tr FROM TimeRecord tr WHERE tr.timeIn IS NOT NULL AND tr.staff = :staff ORDER BY tr.id DESC")
    List<TimeRecord> findLastRecordByStaff(Staff staff);
    List<TimeRecord> findAllByStaff(Staff staff);


}
