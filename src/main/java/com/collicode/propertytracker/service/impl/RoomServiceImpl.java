package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Room;
import com.collicode.propertytracker.infrastructure.repository.RoomRepository;
import com.collicode.propertytracker.service.dto.request.RoomRequestDTO;
import com.collicode.propertytracker.service.spec.RoomService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

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
      room.setCondition(roomRequestDTO.getCondition());
      room.setApartmentId(roomRequestDTO.getApartmentId());
      room.setCreatedAt(LocalDateTime.now());
      roomRepository.save(room);
    } catch (Exception e) {
      throw StorageException.exception("Error while creating room");
    }
  }
}

