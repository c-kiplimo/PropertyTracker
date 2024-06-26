package com.collicode.propertytracker.service.dto.request;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class PaymentRequestDTO {
  private long tenantId;
  private BigDecimal amountPaid;
  private BigDecimal balance;
  private BigDecimal transactionCost;
  private BigDecimal penaltyFee;
  private String transactionType;

}
