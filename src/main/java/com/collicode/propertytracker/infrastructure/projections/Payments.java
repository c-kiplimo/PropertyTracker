package com.collicode.propertytracker.infrastructure.projections;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_Payments")
@Getter
@Setter

public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    private long tenantId;
    private BigDecimal balance;
    private BigDecimal amountDue;
    private BigDecimal transactionCost;
    private LocalDate paymentDate;
    private LocalDate dueDate;
    private long transactionType;
    private BigDecimal amountPaid;
    private BigDecimal penaltyFee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String auditInfo;

}