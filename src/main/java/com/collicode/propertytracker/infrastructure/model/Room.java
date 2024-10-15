package com.collicode.propertytracker.infrastructure.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_rooms")
public class Room {
    @Id
    @SequenceGenerator(
        name = "room_sequence",
        sequenceName = "room_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "room_sequence")
    private long  roomId;
    private String roomName;
    private String condition;
    private long apartmentId;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
    private String auditInfo;



}


