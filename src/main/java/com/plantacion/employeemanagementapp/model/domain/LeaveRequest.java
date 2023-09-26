package com.plantacion.employeemanagementapp.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestBody;
    private String requestTitle;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

}
