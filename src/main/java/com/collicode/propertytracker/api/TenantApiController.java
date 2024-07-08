package com.collicode.propertytracker.api;

import com.collicode.propertytracker.exceptions.StorageException;
import com.collicode.propertytracker.service.dto.request.TenantRequestDTO;
import com.collicode.propertytracker.service.spec.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/tenants")
@RestController
@RequiredArgsConstructor
public class TenantApiController {
    private final TenantService tenantService;
    @PostMapping
    public ResponseEntity<?> createTenant(@RequestHeader("X-RequestId") String requestId,
        @RequestBody TenantRequestDTO tenantRequestDTO) {
        try {
            tenantService.createTenant(tenantRequestDTO);
            return ResponseEntity.ok().body(tenantRequestDTO);
        }catch (StorageException ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
    @GetMapping()
     public  ResponseEntity<?> fetchTenants(@RequestHeader("X-RequestId") String requestId,@PathVariable long tenantId) {
        try {
            return ResponseEntity.ok().body(TenantService.fetchTenantBytenantId(tenantId));

        }catch (StorageException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

}
