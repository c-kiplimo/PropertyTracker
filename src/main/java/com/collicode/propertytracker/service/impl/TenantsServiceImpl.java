package com.collicode.propertytracker.service.impl;


import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.infrastructure.model.Tenant;
import com.collicode.propertytracker.infrastructure.repository.TenantRepository;
import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;
import com.collicode.propertytracker.service.dto.response.TenantResponseDTO;
import com.collicode.propertytracker.service.spec.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TenantsServiceImpl  implements TenantService {

    private static final Logger log = LoggerFactory.getLogger(TenantsServiceImpl.class);
    private final TenantRepository tenantRepository;

    public TenantsServiceImpl( TenantRepository tenantRepository) {
        this.tenantRepository=tenantRepository;
    }

    @Override
    public TenantResponseDTO fetchTenantByTenantId(long tenantId) {
    try {
        Tenant tenant=tenantRepository.findTenantByTenantId(tenantId).orElseThrow(() ->
            EntityNotFoundException.notFound("TENANT NOT FOUND"));
        return TenantResponseDTO.builder()
            .tenantId(tenant.getTenantId())
            .firstName(tenant.getFirstName())
            .address(tenant.getAddress())
            .phoneNumber(tenant.getPhoneNumber())
            .createdAt(tenant.getCreatedAt())
            .idNumber(tenant.getIdNumber())
            .lastName(tenant.getLastName())
            .kraPin(tenant.getKraPin())
            .build();
    }catch (Exception ex){
        throw StorageException.exception("ERROR WHILE FETCHING TENANT");
    }
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


