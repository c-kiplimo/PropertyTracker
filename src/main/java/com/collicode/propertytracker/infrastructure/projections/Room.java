package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private BigDecimal price;


}


