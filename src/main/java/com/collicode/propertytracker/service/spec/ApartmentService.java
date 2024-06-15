package com.collicode.propertytracker.service.spec;


import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;

public interface ApartmentService {
    void  createApartment(ApartmentRequestDTO apartmentRequestDTO);
}
