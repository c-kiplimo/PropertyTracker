package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.projections.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
