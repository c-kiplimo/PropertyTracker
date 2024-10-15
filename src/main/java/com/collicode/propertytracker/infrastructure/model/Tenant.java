package com.collicode.propertytracker.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_tenants")
@Getter
@Setter
public class Tenant {
    @Id
    @SequenceGenerator(
        name = "tenant_sequence",
        sequenceName = "tenant_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tenant_sequence")
    private long tenantId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String kraPin;
    private String email;
    private String idNumber;
    private String passportNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String auditInfo;



}

