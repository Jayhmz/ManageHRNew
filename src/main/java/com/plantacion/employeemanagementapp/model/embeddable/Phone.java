package com.plantacion.employeemanagementapp.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Phone {
    private String number;
    private String countryCode;

    public Phone() {
    }

    public Phone(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

}
