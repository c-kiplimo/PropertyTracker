package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.model.ConfirmationToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken,Long> {
  Optional<ConfirmationToken> findByToken(String token);
}
