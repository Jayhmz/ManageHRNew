package com.plantacion.employeemanagementapp.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String zipCode;
    private String street;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String zipCode, String street, String city, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.country = country;
    }

}
