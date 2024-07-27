package com.collicode.propertytracker.service.dto.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomUpdateRequestDTO {
    private String roomName;
    private String condition;
    private long apartmentId;
    private  LocalDateTime updatedAt;
}
