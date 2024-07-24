package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;
import com.collicode.propertytracker.service.dto.response.TenantResponseDTO;

public interface TenantService {



    TenantResponseDTO fetchTenantByTenantId(long tenantId);

    void createTenant(TenantRequestDTO tenantRequestDTO);
}
