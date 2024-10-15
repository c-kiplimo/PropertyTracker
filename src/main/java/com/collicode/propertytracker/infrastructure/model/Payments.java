package com.collicode.propertytracker.infrastructure.model;

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
    @SequenceGenerator(
        name = "payments_sequence",
        sequenceName = "payments_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payments_sequence")
    private long paymentId;
    private long tenantId;//select
    private BigDecimal balance;
    private BigDecimal amountDue;
    private BigDecimal transactionCost;
    private LocalDate paymentDate;//select
    private LocalDate dueDate;
    private String transactionType;//select
    private BigDecimal amountPaid;//select
    private BigDecimal penaltyFee;
    private LocalDateTime createdAt;//select
    private LocalDateTime updatedAt;
    private String auditInfo;

}