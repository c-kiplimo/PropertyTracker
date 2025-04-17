package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.projections.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
