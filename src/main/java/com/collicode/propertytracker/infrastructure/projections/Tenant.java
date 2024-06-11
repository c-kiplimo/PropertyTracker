package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_Tenant")
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

