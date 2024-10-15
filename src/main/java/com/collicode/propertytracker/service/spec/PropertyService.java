package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.PropertyRequestDTO;
import com.collicode.propertytracker.service.dto.request.PropertyUpdateRequestDTO;

public interface PropertyService {

    void createProperty(PropertyRequestDTO propertyRequestDTO);

    PropertyUpdateRequestDTO updateProperty(long propertyId, PropertyUpdateRequestDTO propertyUpdateRequestDTO);

    String deleteProperty(long propertyId);
}
