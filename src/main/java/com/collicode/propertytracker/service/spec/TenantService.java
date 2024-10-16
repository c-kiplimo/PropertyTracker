package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;
import com.collicode.propertytracker.service.dto.request.TenantUpdateRequestDTO;
import com.collicode.propertytracker.service.dto.response.TenantResponseDTO;

public interface TenantService {


    String deleteTenant(long tenantId);

    TenantResponseDTO fetchTenantByTenantId(long tenantId);

    void createTenant(TenantRequestDTO tenantRequestDTO);

    TenantUpdateRequestDTO updateTenant(long tenantId, TenantUpdateRequestDTO tenantUpdateRequestDTO);
}
