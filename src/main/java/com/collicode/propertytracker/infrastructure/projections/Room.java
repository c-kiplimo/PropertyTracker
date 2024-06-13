package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  roomId;
    private String condition;
    private long apartmentId;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
    private String auditInfo;



}


