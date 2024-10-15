package com.collicode.propertytracker.service.dto;

import com.collicode.propertytracker.infrastructure.model.enums.AgentStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDTO {
  private Long agentCode;
  private String firstName;
  private String lastName;
  private String fullName;
  private String msisdn;
  private String emailAddress;
  private AgentStatus status;
  private Boolean onboarded;
  private String reason;
  private String refId;
  private String knewUsThrough;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
