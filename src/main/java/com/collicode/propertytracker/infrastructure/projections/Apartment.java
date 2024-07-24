package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table (name = "tbl_apartments")
@Getter
@Setter
public class Apartment {
    @Id
    @SequenceGenerator(
        name = "apartments_sequence",
        sequenceName = "apartments_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "apartments_sequence")
    private long apartmentId;
    private long agentCode;
    private String apartmentName;
    private String address;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String auditInfo;

}


