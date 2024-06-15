package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Apartment;
import com.collicode.propertytracker.infrastructure.repository.ApartmentRepository;
import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.spec.ApartmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApartmentServiceImpl implements ApartmentService {


    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public void createApartment(ApartmentRequestDTO apartmentRequestDTO) {
        try {
            Apartment apartment = new Apartment();
            apartment.setApartmentId(apartmentRequestDTO.getApartmentId());
            apartment.setAgentCode(apartmentRequestDTO.getAgentCode());
            apartment.setApartmentName(apartmentRequestDTO.getApartmentName());
            apartment.setCreatedAt(LocalDateTime.now());
            apartmentRepository.save(apartment);
        } catch (Exception e) {
            throw StorageException.exception("ERROR WHILE CREATING APARTMENT");
        }

    }
}

