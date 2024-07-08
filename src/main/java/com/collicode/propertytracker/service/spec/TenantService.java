package com.collicode.propertytracker.service.spec;

import com.collicode.propertytracker.service.dto.request.ApartmentRequestDTO;
import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;

public interface TenantService {

    static Object fetchTenantBytenantId(long tenantId) {
        return null;
    }

    void createTenant(TenantRequestDTO tenantRequestDTO);
}
