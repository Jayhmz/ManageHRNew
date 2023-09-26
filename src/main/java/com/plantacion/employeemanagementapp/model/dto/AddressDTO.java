package com.plantacion.employeemanagementapp.model.dto;

public class AddressDTO {
    private String zipCode;
    private String street;
    private String city;
    private String country;

    public AddressDTO() {
    }

    public AddressDTO(String zipCode, String street, String city, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
