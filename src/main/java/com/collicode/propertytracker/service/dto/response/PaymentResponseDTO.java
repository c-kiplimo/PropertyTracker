package com.collicode.propertytracker.service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponseDTO {
  private long paymentId;
  private long tenantId;
  private LocalDate paymentDate;
  private BigDecimal balance;
  private BigDecimal amountPaid;
  private LocalDateTime createdAt;

}
