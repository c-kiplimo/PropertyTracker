package com.collicode.propertytracker.service.impl;


import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.projections.Tenant;
import com.collicode.propertytracker.infrastructure.repository.TenantRepository;
import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;
import com.collicode.propertytracker.service.spec.TenantService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TenantsServiceImpl  implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantsServiceImpl( TenantRepository tenantRepository) {
        this.tenantRepository=tenantRepository;
    }
    @Override
    public void createTenant(TenantRequestDTO tenantRequestDTO) {
        try {
            Tenant tenant = new Tenant();
            tenant.setFirstName(tenantRequestDTO.getFirstName());
            tenant.setEmail(tenantRequestDTO.getEmail());
            tenant.setLastName(tenantRequestDTO.getLastName());
            tenant.setPhoneNumber(tenantRequestDTO.getPhoneNumber());
            tenant.setIdNumber(tenantRequestDTO.getIdNumber());
            tenant.setPassportNumber(tenantRequestDTO.getPassportNumber());
            tenant.setKraPin(tenantRequestDTO.getKraPin());
            tenant.setCreatedAt(LocalDateTime.now());
            tenantRepository.save (tenant);
        } catch (Exception e){
          throw StorageException.exception("ERROR WHILE CREATING TENANT");
        }
    }
    }


