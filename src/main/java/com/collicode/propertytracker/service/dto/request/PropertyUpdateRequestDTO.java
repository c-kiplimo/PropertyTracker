package com.collicode.propertytracker.service.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyUpdateRequestDTO {
    private String description;
    private String propertyName;
    private BigDecimal price;
}
