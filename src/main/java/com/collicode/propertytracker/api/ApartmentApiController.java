package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Apartment;
import com.collicode.propertytracker.infrastructure.repository.ApartmentRepository;
import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.dto.request.ApartmentUpdateRequestDTO;
import com.collicode.propertytracker.service.spec.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/apartments")
@RestController
@RequiredArgsConstructor
public class ApartmentApiController {
    private final ApartmentService apartmentService;
    private final ApartmentRepository apartmentRepository;

    @PostMapping
    public ResponseEntity<?> createApartment(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
            @RequestBody ApartmentRequestDTO apartmentRequestDTO) {
        try {
            apartmentService.createApartment(apartmentRequestDTO);
            return ResponseEntity.ok().body(apartmentRequestDTO);
        } catch (StorageException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @PutMapping("/{apartmentId}")
    public ResponseEntity<?> updateApartment(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
            @PathVariable long apartmentId,
            @RequestBody ApartmentUpdateRequestDTO apartmentUpdateRequestDTO) {
        try {
            apartmentService.updateApartment(apartmentId, apartmentUpdateRequestDTO);
            return ResponseEntity.ok().body(apartmentUpdateRequestDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (StorageException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{apartmentId}")
    public ResponseEntity<?> deleteApartment(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
            @PathVariable long apartmentId) {
        try {
            return ResponseEntity.ok().body(apartmentService.deleteApartment(apartmentId));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (StorageException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return ResponseEntity.ok().body(apartments);
    }

    @GetMapping("/{apartmentId}")
    public ResponseEntity<?> getApartmentById(
            @RequestHeader(value = "X-RequestId", required = false) String requestId,
            @PathVariable long apartmentId) {
        try {
            Apartment apartment = apartmentRepository.findById(apartmentId)
                    .orElseThrow(() -> new EntityNotFoundException("Apartment not found"));
            return ResponseEntity.ok().body(apartment);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
