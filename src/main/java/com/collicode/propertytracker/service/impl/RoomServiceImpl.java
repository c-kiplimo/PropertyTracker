package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Room;
import com.collicode.propertytracker.infrastructure.repository.RoomRepository;
import com.collicode.propertytracker.service.dto.request.RoomRequestDTO;
import com.collicode.propertytracker.service.dto.request.RoomUpdateRequestDTO;
import com.collicode.propertytracker.service.dto.response.RoomResponseDTO;
import com.collicode.propertytracker.service.spec.RoomService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@Service
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;

  public RoomServiceImpl(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @Override
  public void createRoom(RoomRequestDTO roomRequestDTO) {
    try {
      Room room = new Room();
      room.setRoomName(roomRequestDTO.getRoomName());
      room.setCondition(roomRequestDTO.getCondition());
      room.setApartmentId(roomRequestDTO.getApartmentId());
      room.setCreatedAt(LocalDateTime.now());
      roomRepository.save(room);
    } catch (Exception e) {
      throw StorageException.exception("Error while creating room");
    }
  }

  @Override
  public List<RoomResponseDTO> fetchRoomsByApartmentId(long apartmentId) {
      List<Room> rooms = roomRepository.findByApartmentId(apartmentId);
      return rooms.stream().map(room -> RoomResponseDTO.builder()
          .roomId(room.getRoomId())
          .roomName(room.getRoomName())
          .condition(room.getCondition())
          .apartmentId(room.getApartmentId())
          .createdAt(room.getCreatedAt())
          .build()).toList();
  }

  @Override
  public void updateRoom(long roomId, RoomUpdateRequestDTO roomUpdateRequestDTO) {

  }

  @Override
  public String deleteApartment(long roomId) {
    return "";
  }
}

