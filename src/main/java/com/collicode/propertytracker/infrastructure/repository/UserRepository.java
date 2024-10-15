package com.collicode.propertytracker.infrastructure.repository;

import com.collicode.propertytracker.infrastructure.model.User;
import com.collicode.propertytracker.service.dto.UserDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


  Optional<User> findUserByUsername(String username);

  Optional<UserDTO> findUserById(Long userId);

  Optional<User> findByMsisdn(String msisdn);

  Optional<User> findByEmail(String userEmail);
}
