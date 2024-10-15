package com.collicode.propertytracker.service.mapper;

import com.collicode.propertytracker.infrastructure.model.Agent;
import com.collicode.propertytracker.infrastructure.model.User;
import com.collicode.propertytracker.service.dto.AgentDTO;
import com.collicode.propertytracker.service.dto.UserDTO;
import java.util.function.Function;

public class EntityToDtoMapper {

  public static Function<User, UserDTO> userToUserDtoMapper(){
    return user -> UserDTO.builder()
        .email(user.getEmail())
        .msisdn(user.getMsisdn())
        .fullName(user.getFullName())
        .username(user.getUsername())
        .userRole(user.getUserRole())
        .build();
  }
  public static Function<Agent, AgentDTO>agentToAgentDtoMapper(){
    return agent -> AgentDTO.builder()
        .agentCode(agent.getAgentCode())
        .firstName(agent.getFirstName())
        .lastName(agent.getLastName())
        .fullName(agent.getFullName())
        .msisdn(agent.getMsisdn())
        .emailAddress(agent.getEmailAddress())
        .status(agent.getStatus())
        .onboarded(agent.getOnboarded())
        .reason(agent.getReason())
        .refId(agent.getRefId())
        .knewUsThrough(agent.getKnewUsThrough())
        .createdAt(agent.getCreatedAt())
        .updatedAt(agent.getUpdatedAt())
        .build();
  }

  public static  Function<AgentDTO,Agent> agentDtoToAgentMapper(){
    return agentDTO -> Agent.builder()
        .agentCode(agentDTO.getAgentCode())
        .firstName(agentDTO.getFirstName())
        .lastName(agentDTO.getLastName())
        .fullName(agentDTO.getFullName())
        .msisdn(agentDTO.getMsisdn())
        .emailAddress(agentDTO.getEmailAddress())
        .status(agentDTO.getStatus())
        .onboarded(agentDTO.getOnboarded())
        .reason(agentDTO.getReason())
        .refId(agentDTO.getRefId())
        .knewUsThrough(agentDTO.getKnewUsThrough())
        .createdAt(agentDTO.getCreatedAt())
        .updatedAt(agentDTO.getUpdatedAt())
        .build();
  }
}
