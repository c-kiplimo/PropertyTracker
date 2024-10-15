package com.collicode.propertytracker.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PropertyResponseDTO {
    private long propertyId;
    private String description;
    private String propertyName;
    private BigDecimal price;
    private String address;
    private String location;
}
