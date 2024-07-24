package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Apartment;
import com.collicode.propertytracker.infrastructure.repository.ApartmentRepository;
import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.dto.request.ApartmentUpdateRequestDTO;
import com.collicode.propertytracker.service.spec.ApartmentService;
import java.util.Objects;
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
            apartment.setAgentCode(apartmentRequestDTO.getAgentCode());
            apartment.setApartmentName(apartmentRequestDTO.getApartmentName());
            apartment.setAddress(apartmentRequestDTO.getAddress());
            apartment.setLocation(apartmentRequestDTO.getLocation());
            apartment.setCreatedAt(LocalDateTime.now());
            apartmentRepository.save(apartment);
        } catch (Exception e) {
            throw StorageException.exception("ERROR WHILE CREATING APARTMENT");
        }

    }

    @Override
    public ApartmentUpdateRequestDTO updateApartment(long appartmentId,ApartmentUpdateRequestDTO apartmentUpdateRequestDTO) {
        try {
            Apartment apartment = apartmentRepository.findById(appartmentId)
                .orElseThrow(() -> EntityNotFoundException.notFound("APARTMENT NOT FOUND"));
            if (Objects.nonNull(apartmentUpdateRequestDTO.getApartmentName())){
                apartment.setApartmentName(apartmentUpdateRequestDTO.getApartmentName());
            }
            apartmentRepository.save(apartment);
        }catch (Exception e){
            throw StorageException.exception("ERROR WHILE UPDATING APARTMENT");
        }
      return apartmentUpdateRequestDTO;
    }

    @Override
    public String deleteApartment(long apartmentId) {
        Apartment apartment = apartmentRepository.findById(apartmentId)
            .orElseThrow(() -> EntityNotFoundException.notFound("APARTMENT NOT FOUND"));
        try{
            apartmentRepository.delete(apartment);
            return "APARTMENT DELETED";
        }catch (Exception e){
            throw StorageException.exception("ERROR WHILE DELETING APARTMENT");
        }
    }


}

