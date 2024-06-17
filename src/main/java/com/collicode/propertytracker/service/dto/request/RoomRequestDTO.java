package com.collicode.propertytracker.service.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequestDTO {
  private String condition;
  private String roomName;
  private long apartmentId;

}
