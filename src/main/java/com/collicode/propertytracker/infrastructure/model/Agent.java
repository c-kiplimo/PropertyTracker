package com.collicode.propertytracker.infrastructure.model;

import com.collicode.propertytracker.infrastructure.model.enums.AgentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "tbl_agents")
@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Agent {

  @Id
  @SequenceGenerator(
      name = "agent_sequence",
      sequenceName = "agent_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agent_sequence")
  private Long agentCode;
  private String firstName;
  private String lastName;
  private String fullName;
  private String msisdn;
  private String emailAddress;
  @Enumerated(EnumType.STRING)
  private AgentStatus status;
  private Boolean onboarded;
  private String reason;
  private String refId;
  private String knewUsThrough;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String auditInfo;



}
