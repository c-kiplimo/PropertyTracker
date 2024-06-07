package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tenantId;
    private String firstName;
    private String lastName;
    private String address;
    private long phoneNumber;
    private String kraPin;
    private String email;
    private long idNumber;
    private long passportNumber;


}

