package com.collicode.propertytracker.service.dto.request;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentUpdateRequestDTO {
    private Long paymentId;
    private BigDecimal amountDue;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private LocalDate dueDate;
    private BigDecimal paidAmount;
}
