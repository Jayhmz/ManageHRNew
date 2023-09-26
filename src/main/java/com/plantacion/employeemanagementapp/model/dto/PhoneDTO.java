package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PhoneDTO {
    @NotNull
    @Size(min = 9, max = 11, message = "Enter valid phone number")
    private String number;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 4, message = "Enter valid country code")
    private String countryCode;

    public PhoneDTO() {
    }

    public PhoneDTO(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
