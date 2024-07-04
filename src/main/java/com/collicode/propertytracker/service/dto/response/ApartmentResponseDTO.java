package com.collicode.propertytracker.service.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApartmentResponseDTO {
    private long apartmentId;
    private long agentCode;
    private String apartmentName;
    private String address;
    private String location;
    private LocalDateTime createdAt;
}
