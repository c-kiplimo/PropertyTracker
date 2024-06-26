package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Payments;
import com.collicode.propertytracker.infrastructure.repository.PaymentRepository;
import com.collicode.propertytracker.service.dto.request.PaymentRequestDTO;
import com.collicode.propertytracker.service.dto.response.PaymentResponseDTO;
import com.collicode.propertytracker.service.spec.PaymentsService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl  implements PaymentsService{
  private final PaymentRepository paymentRepository;

  public PaymentsServiceImpl(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public void createPayment(PaymentRequestDTO paymentRequestDTO) {
    try {
      Payments payments = new Payments();
      payments.setAmountPaid(paymentRequestDTO.getAmountPaid());
      payments.setTenantId(paymentRequestDTO.getTenantId());
      payments.setTransactionType(paymentRequestDTO.getTransactionType());
      payments.setPaymentDate(LocalDate.now());
      payments.setCreatedAt(LocalDateTime.now());
      paymentRepository.save(payments);
    }catch (Exception e){
      throw StorageException.exception("Error while creating payment");
    }

  }

  @Override
  public List<PaymentResponseDTO> fetchPaymentByTenantId(long tenantId) {
    //initialize by calling the repository method to fetch payment by tenantId
    List<Payments> fetchedPayments = paymentRepository.findByTenantId(tenantId);
     //map the fetched payments to the response dto
    return getPaymentsDtoMapper(fetchedPayments);
  }

  private static List<PaymentResponseDTO> getPaymentsDtoMapper(List<Payments> fetchedPayments) {
    return fetchedPayments.stream().map(payments -> PaymentResponseDTO.builder()
        .tenantId(payments.getTenantId())
        .paymentDate(payments.getPaymentDate())
        .balance(payments.getBalance())
        .amountPaid(payments.getAmountPaid())
        .createdAt(payments.getCreatedAt())
        .build()).toList();
  }
}
