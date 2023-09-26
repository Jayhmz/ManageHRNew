package com.plantacion.employeemanagementapp.model.domain;

import com.plantacion.employeemanagementapp.model.embeddable.Address;
import com.plantacion.employeemanagementapp.model.embeddable.Phone;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipcode")),
            @AttributeOverride(name = "country", column = @Column(name = "home_country"))
    })
    private Address primaryHomeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "office_street")),
            @AttributeOverride(name = "city", column = @Column(name = "office_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "office_zipcode")),
            @AttributeOverride(name = "country", column = @Column(name = "office_country"))
    })
    private Address secondaryHomeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "land_line")),
            @AttributeOverride(name = "countryCode", column = @Column(name = "land_line_c_code"))
    })
    private Phone mobile;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "hot_line")),
            @AttributeOverride(name = "countryCode", column = @Column(name = "hot_line_c_code"))
    })
    private Phone hotline;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "staff_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Address getPrimaryHomeAddress() {
        return primaryHomeAddress;
    }

    public void setPrimaryHomeAddress(Address primaryHomeAddress) {
        this.primaryHomeAddress = primaryHomeAddress;
    }

    public Address getSecondaryHomeAddress() {
        return secondaryHomeAddress;
    }

    public void setSecondaryHomeAddress(Address secondaryHomeAddress) {
        this.secondaryHomeAddress = secondaryHomeAddress;
    }

    public Phone getMobile() {
        return mobile;
    }

    public void setMobile(Phone mobile) {
        this.mobile = mobile;
    }

    public Phone getHotline() {
        return hotline;
    }

    public void setHotline(Phone hotline) {
        this.hotline = hotline;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
