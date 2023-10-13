package com.plantacion.employeemanagementapp.service.impl;

import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.domain.TimeRecord;
import com.plantacion.employeemanagementapp.repository.StaffRepository;
import com.plantacion.employeemanagementapp.repository.TimeRecordRepository;
import com.plantacion.employeemanagementapp.service.TimeRecordService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;
    private final StaffRepository staffRepository;

    public TimeRecordServiceImpl(TimeRecordRepository timeRecordRepository, StaffRepository staffRepository) {
        this.timeRecordRepository = timeRecordRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional
    public boolean signInTime(String staffEmail) {
        Staff staff = staffRepository.findByEmail(staffEmail);
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setTimeIn(LocalDateTime.now());
        timeRecord.setStaff(staff);
        try {
            timeRecordRepository.save(timeRecord);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean signOutTime(String staffEmail) {
        Staff staff = staffRepository.findByEmail(staffEmail);
        TimeRecord timeRecord = timeRecordRepository.findLastRecordByStaff(staff).get(0);
        timeRecord.setTimeOut(LocalDateTime.now());
        try {
            timeRecordRepository.save(timeRecord);
            return true;
        }catch (Exception e){
            System.out.println("there is an error in the time out block");
            return false;
        }
    }

    @Override
    public double calcHoursWorked(Long staffId){
        Staff staff = staffRepository.findById(staffId).get();
        System.out.println(staff);
        double totalHours = 0.0;
        if (staff != null){
            List<TimeRecord> staffTimeRecords = timeRecordRepository.findAllByStaff(staff);
            System.out.println("size of stafftime record is "+staffTimeRecords.size());
            for(TimeRecord timeRecord : staffTimeRecords){
                LocalDateTime timeIn = timeRecord.getTimeIn();
                LocalDateTime timeOut = timeRecord.getTimeOut();
                if (timeIn != null && timeOut != null) {
                    Duration duration = Duration.between(timeIn, timeOut);
                    totalHours += duration.toHours();
                }
            }
        }
        return totalHours;
    }

}
