package com.collicode.propertytracker.service.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseDTO {
  private long  roomId;
  private String roomName;
  private String condition;
  private long apartmentId;
  private LocalDateTime createdAt;
}
