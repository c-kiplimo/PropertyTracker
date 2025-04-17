package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.service.dto.request.PropertyUpdateRequestDTO;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Property;
import com.collicode.propertytracker.infrastructure.repository.PropertyRepository;
import com.collicode.propertytracker.service.dto.request.PropertyRequestDTO;
import com.collicode.propertytracker.service.spec.PropertyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PropertyServiceImpl implements PropertyService {

    private  final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @Override
    public void createProperty(PropertyRequestDTO propertyRequestDTO) {
        try {
            Property property = new Property();
            property.setDescription(propertyRequestDTO.getDescription());
            property.setPropertyName(propertyRequestDTO.getPropertyName());
            property.setPrice(propertyRequestDTO.getPrice());
            property.setAddress(propertyRequestDTO.getAddress());
            property.setLocation(propertyRequestDTO.getLocation());
            property.setCreatedAt(LocalDateTime.now());
            propertyRepository.save(property);
        } catch (Exception e) {
            throw StorageException.exception("ERROR WHILE CREATING PROPERTY");
        }

    }
    @Override
    public PropertyUpdateRequestDTO updateProperty(long propertyId, PropertyUpdateRequestDTO propertyUpdateRequestDTO) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> EntityNotFoundException.notFound("PROPERTY NOT FOUND"));
            if (Objects.nonNull(propertyUpdateRequestDTO.getPropertyName())){
                property.setPropertyName(propertyUpdateRequestDTO.getPropertyName());
            }
            propertyRepository.save(property);
        }catch (Exception e){
            throw StorageException.exception("ERROR WHILE UPDATING PROPERTY");
        }
        return propertyUpdateRequestDTO;
    }
    @Override
    public String deleteProperty(long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> EntityNotFoundException.notFound("PROPERTY NOT FOUND"));
        try{
            propertyRepository.delete(property);
            return "PROPERTY DELETED";
        }catch (Exception e){
            throw StorageException.exception("ERROR WHILE DELETING PROPERTY");
        }
    }


}
