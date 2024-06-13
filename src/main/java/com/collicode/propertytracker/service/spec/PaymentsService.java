package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.PaymentRequestDTO;

public interface PaymentsService {
  void createPayment(PaymentRequestDTO paymentRequestDTO);

}
