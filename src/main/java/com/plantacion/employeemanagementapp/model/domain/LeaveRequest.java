package com.plantacion.employeemanagementapp.model.domain;

import com.plantacion.employeemanagementapp.model.enums.LeaveApproval;
import jakarta.persistence.*;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leaveBody;
    private String leaveSubject;

    @Enumerated(EnumType.STRING)
    private LeaveApproval leaveApproval;

    public LeaveApproval getLeaveApproval() {
        return leaveApproval;
    }

    public void setLeaveApproval(LeaveApproval leaveApproval) {
        this.leaveApproval = leaveApproval;
    }

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveBody() {
        return leaveBody;
    }

    public void setLeaveBody(String leaveBody) {
        this.leaveBody = leaveBody;
    }

    public String getLeaveSubject() {
        return leaveSubject;
    }

    public void setLeaveSubject(String leaveSubject) {
        this.leaveSubject = leaveSubject;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

}
