package com.collicode.propertytracker.service.spec;


import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.dto.request.ApartmentUpdateRequestDTO;

public interface ApartmentService {
    void  createApartment(ApartmentRequestDTO apartmentRequestDTO);
    ApartmentUpdateRequestDTO updateApartment(long appartmentId,ApartmentUpdateRequestDTO apartmentUpdateRequestDTO);
    String deleteApartment(long apartmentId);

}
