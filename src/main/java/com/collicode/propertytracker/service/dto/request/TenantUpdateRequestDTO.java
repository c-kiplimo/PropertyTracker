package com.collicode.propertytracker.service.dto.request;


import lombok.Data;

@Data
public class TenantUpdateRequestDTO {
    private Long tenantId;
    private String phoneNumber;
}
