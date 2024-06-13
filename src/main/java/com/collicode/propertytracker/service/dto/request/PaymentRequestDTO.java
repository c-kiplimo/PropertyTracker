package com.collicode.propertytracker.service.dto.request;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class PaymentRequestDTO {
  private long tenantId;
  private BigDecimal amountPaid;
  private String transactionType;

}
