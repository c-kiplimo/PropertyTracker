package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.model.Agent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {

  Optional<Agent> findByAgentCode(long agentCode);

  boolean existsByAgentCode(long agentCode);
}
