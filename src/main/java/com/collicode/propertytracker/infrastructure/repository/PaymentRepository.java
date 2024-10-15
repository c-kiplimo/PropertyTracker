package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.model.Payments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

  List<Payments> findByTenantId(long tenantId);
}
