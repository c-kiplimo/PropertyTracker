package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.service.dto.request.RoomRequestDTO;
import com.collicode.propertytracker.service.spec.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/rooms")
@RestController
@RequiredArgsConstructor
public class RoomApiController {
  private final RoomService roomService;

  public ResponseEntity<?> createRoom(@RequestHeader("X-RequestId") String requestId,
      @RequestBody RoomRequestDTO roomRequestDTO) {
    try {
      roomService.createRoom(roomRequestDTO);
      return ResponseEntity.ok().body(roomRequestDTO);
    }catch (StorageException ex){
      return ResponseEntity.status(400).body(ex.getMessage());
    }catch (Exception ex){
      return ResponseEntity.status(500).body(ex.getMessage());
    }
  }

}
