package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.service.dto.request.ApartmentUpdateRequestDTO;
import com.collicode.propertytracker.service.dto.request.RoomRequestDTO;
import com.collicode.propertytracker.service.dto.request.RoomUpdateRequestDTO;
import com.collicode.propertytracker.service.spec.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/rooms")
@RestController
@RequiredArgsConstructor
public class RoomApiController {
  private final RoomService roomService;
@PostMapping
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
  @GetMapping("/{apartmentId}")
  public ResponseEntity<?> fetchRoomsByApartmentId(@RequestHeader("X-RequestId") String requestId,@PathVariable long apartmentId) {
    try {
      return ResponseEntity.ok().body(roomService.fetchRoomsByApartmentId(apartmentId));
    } catch (Exception ex) {
      return ResponseEntity.status(500).body(ex.getMessage());
    }
  }
  @PutMapping("/{apartmentId}")
  public ResponseEntity<?> updateRoom(@RequestHeader("X-RequestId") String requestId,
                                           @PathVariable long roomId,
                                           @RequestBody RoomUpdateRequestDTO roomUpdateRequestDTO) {
    try {
      roomService.updateRoom(roomId, roomUpdateRequestDTO);
      return ResponseEntity.ok().body(roomUpdateRequestDTO);
    }catch (EntityNotFoundException ex){
      return ResponseEntity.status(404).body(ex.getMessage());
    }catch (StorageException ex){
      return ResponseEntity.status(500).body(ex.getMessage());
    }
  }
  @DeleteMapping("/apartmentId")
  public ResponseEntity<?> deleteRoom(@RequestHeader("X-RequestId") String requestId,
                                           @PathVariable long roomId) {
    try {
      return ResponseEntity.ok().body(roomService.deleteApartment(roomId));
    }catch (EntityNotFoundException ex){
      return ResponseEntity.status(404).body(ex.getMessage());
    }catch (StorageException ex){
      return ResponseEntity.status(500).body(ex.getMessage());
    }
  }


}
