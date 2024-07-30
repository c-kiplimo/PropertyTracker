package com.collicode.propertytracker.service.dto.request;


import lombok.Getter;


@Getter
public class ApartmentRequestDTO {
    private long apartmentId;
    private  long agentCode;
    private String apartmentName;
    private String address;
    private String location;
}


