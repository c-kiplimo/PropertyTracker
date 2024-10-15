package com.collicode.propertytracker.service;

import static com.collicode.propertytracker.service.mapper.EntityToDtoMapper.agentDtoToAgentMapper;
import static com.collicode.propertytracker.service.mapper.EntityToDtoMapper.agentToAgentDtoMapper;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.infrastructure.model.Agent;
import com.collicode.propertytracker.infrastructure.repository.AgentRepository;
import com.collicode.propertytracker.service.dto.AgentDTO;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {
  private final AgentRepository agentRepository;

  @Transactional
  public AgentDTO save(AgentDTO agentDTO) {
    if (Objects.isNull(agentDTO.getAgentCode())) {
      agentDTO.setCreatedAt(LocalDateTime.now());
    }else {
      agentDTO.setUpdatedAt(LocalDateTime.now());
    }

    Agent agent = agentDtoToAgentMapper().apply(agentDTO);
    agent = agentRepository.save(agent);

    return agentToAgentDtoMapper().apply(agent);
  }


  public Optional<Agent> getAgentByAgentCode(long agentCode){
    Optional<Agent> agent = agentRepository.findByAgentCode(agentCode);

    if (agent.isPresent()){
      return agent;
    }else {
      throw EntityNotFoundException.notFound("Agent not found");
    }
  }
public Optional<AgentDTO>findOne(long agentCode){
    log.info("Fetching agent with agentCode: {}", agentCode);
    return agentRepository.findByAgentCode(agentCode).map(agentToAgentDtoMapper());
}

public  String deleteAgent(long agentCode){
    boolean agentExists = agentRepository.existsByAgentCode(agentCode);
    if (!agentExists){
        throw EntityNotFoundException.notFound("Agent not found");
    }
    agentRepository.deleteById(agentCode);
    return "Agent deleted successfully";
}

//  public AgentDTO getAgentMetaData(AgentDTO agent) {
//    Long agentCode = agent.getAgentCode();
//
//  }

  public Agent addNewAgent(Agent agent) {
    if (Objects.isNull(agent.getAgentCode())) {
      agent.setCreatedAt(LocalDateTime.now());
      agent.setReason("REG_REQUEST");
    }
    return agentRepository.save(agent);
  }
}
