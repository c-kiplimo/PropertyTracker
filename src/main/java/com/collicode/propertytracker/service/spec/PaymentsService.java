package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.PaymentRequestDTO;
import com.collicode.propertytracker.service.dto.response.PaymentResponseDTO;
import java.util.List;

public interface PaymentsService {
  void createPayment(PaymentRequestDTO paymentRequestDTO);
  List<PaymentResponseDTO> fetchPaymentByTenantId(long tenantId);

}
