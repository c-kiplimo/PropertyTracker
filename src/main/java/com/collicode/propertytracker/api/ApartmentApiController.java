package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.spec.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/apartments")
@RestController
@RequiredArgsConstructor
public class ApartmentApiController {
    private final ApartmentService apartmentService;
    @PostMapping
    public ResponseEntity<?> createApartment(@RequestHeader("X-RequestId") String requestId,
                                             @RequestBody ApartmentRequestDTO apartmentRequestDTO) {
        try {
            apartmentService.createApartment(apartmentRequestDTO);
            return ResponseEntity.ok().body(apartmentRequestDTO);
        }catch (StorageException ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

}
