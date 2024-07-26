package com.collicode.propertytracker.service.spec;


import com.collicode.propertytracker.service.dto.request.RoomRequestDTO;
import com.collicode.propertytracker.service.dto.request.RoomUpdateRequestDTO;
import com.collicode.propertytracker.service.dto.response.RoomResponseDTO;
import java.util.List;

public interface RoomService {

  void createRoom(RoomRequestDTO roomRequestDTO);
  List<RoomResponseDTO> fetchRoomsByApartmentId(long apartmentId);

    void updateRoom(long roomId, RoomUpdateRequestDTO roomUpdateRequestDTO);

  String deleteApartment(long roomId);
}
