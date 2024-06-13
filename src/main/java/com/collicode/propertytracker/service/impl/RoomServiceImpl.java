package com.collicode.propertytracker.service.impl;

import com.collicode.propertytracker.infrastructure.projections.Room;
import com.collicode.propertytracker.infrastructure.repository.RoomRepository;
import com.collicode.propertytracker.service.spec.RoomService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RoomServiceImpl{

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public Room createRoom(Room room) {
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());
        return roomRepository.save(room);
    }

    public Room updateRoom(Long roomId, Room roomDetails) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            room.setCondition(roomDetails.getCondition());
            room.setApartmentId(roomDetails.getApartmentId());
            room.setUpdatedAt(LocalDateTime.now());
            room.setAuditInfo(roomDetails.getAuditInfo());
            return roomRepository.save(room);
        } else {
            return null;
        }
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public List<Room> getRoomsByApartmentId(Long apartmentId) {
        return roomRepository.findAllById(Collections.singleton(apartmentId));
    }
}

