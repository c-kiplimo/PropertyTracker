package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Apartment;
import com.collicode.propertytracker.infrastructure.projections.Payments;
import com.collicode.propertytracker.infrastructure.repository.PaymentRepository;
import com.collicode.propertytracker.service.dto.request.PaymentRequestDTO;
import com.collicode.propertytracker.service.dto.request.PaymentUpdateRequestDTO;
import com.collicode.propertytracker.service.spec.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/payments")
@RestController
@RequiredArgsConstructor
public class PaymentApiController {
    private final PaymentsService paymentService;
    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<?> createPayment(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
                                           @RequestBody PaymentRequestDTO paymentRequestDTO) {
        try {
            paymentService.createPayment(paymentRequestDTO);
            return ResponseEntity.ok().body(paymentRequestDTO);
        } catch (StorageException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Payments>> getPayments() {
        List<Payments> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<?> fetchPaymentByTenantId(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
            @PathVariable long tenantId) {
        try {
            Payments payments = paymentRepository.findById(tenantId)
                    .orElseThrow(() -> new EntityNotFoundException("PAYMENT NOT  FOUND"));
            return ResponseEntity.ok().body(payments);
        } catch (StorageException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }


    @PutMapping("/{paymentId}")
    public ResponseEntity<?> updatepayment(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
                                             @PathVariable long paymentId,
                                           @RequestBody PaymentUpdateRequestDTO paymentUpdateRequestDTO) {
        try {
            paymentService.updatePayment(paymentId, paymentUpdateRequestDTO);
            return ResponseEntity.ok().body(paymentUpdateRequestDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (StorageException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

            //update
            //delete


        }