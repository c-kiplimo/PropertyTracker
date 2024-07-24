package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.service.dto.request.PaymentRequestDTO;
import com.collicode.propertytracker.service.dto.request.PaymentUpdateRequestDTO;
import com.collicode.propertytracker.service.spec.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/payments")
@RestController
@RequiredArgsConstructor
public class PaymentApiController {
    private final PaymentsService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestHeader("X-RequestId") String requestId,
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

    @GetMapping("/{tenantId}")
    public ResponseEntity<?> fetchPaymentByTenantId(@RequestHeader("X-RequestId") String requestId, @PathVariable long tenantId) {
        try {
            return ResponseEntity.ok().body(paymentService.fetchPaymentByTenantId(tenantId));
        } catch (StorageException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }


    @PutMapping("/{paymentId}")
    public ResponseEntity<?> updatepayment(@RequestHeader("X-RequestId") String requestId,
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