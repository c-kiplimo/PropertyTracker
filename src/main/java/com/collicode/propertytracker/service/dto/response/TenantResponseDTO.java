package com.collicode.propertytracker.service.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TenantResponseDTO {
    private long tenantId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String kraPin;
    private String email;
    private String idNumber;
    private String passportNumber;
    private LocalDateTime createdAt;

}
