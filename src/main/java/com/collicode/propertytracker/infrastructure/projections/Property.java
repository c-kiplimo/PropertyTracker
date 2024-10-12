package com.collicode.propertytracker.infrastructure.projections;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class Property {
    private long propertyId;
    private String description;
    private String propertyName;
    private BigDecimal price;
    private String address;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String auditInfo;

}
