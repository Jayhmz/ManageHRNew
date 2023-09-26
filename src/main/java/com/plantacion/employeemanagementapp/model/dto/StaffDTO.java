package com.plantacion.employeemanagementapp.model.dto;

import com.plantacion.employeemanagementapp.model.domain.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class StaffDTO {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    private AddressDTO address;
    private PhoneDTO phone;
    @NotNull
    @Min(value = 6, message = "password length is too small")
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private Set<RoleDTO> roles;

    public StaffDTO() {
    }

    public StaffDTO(String firstname, String lastname, String email,
                    AddressDTO address, PhoneDTO phone, String password,
                    String confirmPassword, Set<RoleDTO> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles +
                '}';
    }
}
