package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.projections.Tenant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

  Optional<Tenant> findTenantByTenantId(long tenantId);
}
