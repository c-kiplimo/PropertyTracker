package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private double price;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Room> rooms;

    // Getters and setters
}


