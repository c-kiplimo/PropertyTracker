package com.collicode.propertytracker.service.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PropertyRequestDTO {
    private long propertyId;
    private String description;
    private String propertyName;
    private BigDecimal price;
    private String address;
    private String location;
}
