package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.model.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession,Long> {

  UserSession findByUserId(Long userId);

  void deleteByUserId(Long userId);
}
