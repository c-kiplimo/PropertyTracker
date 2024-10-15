package com.collicode.propertytracker.service;

import static com.collicode.propertytracker.service.mapper.EntityToDtoMapper.userToUserDtoMapper;

import com.collicode.propertytracker.exceptions.EntityNotFoundException;
import com.collicode.propertytracker.infrastructure.model.User;
import com.collicode.propertytracker.infrastructure.repository.AgentRepository;
import com.collicode.propertytracker.infrastructure.repository.UserRepository;
import com.collicode.propertytracker.notification.NotificationServiceHTTPClient;
import com.collicode.propertytracker.service.dto.UserDTO;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

  private final static String USER_NOT_FOUND_MSG = "Phone Number %s not found!";
  private final static String USER_EXISTS = "Phone %s Taken!";
  private final static String EMAIL_NOT_VALID = "EMAIL %s IS NOT VALID";

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  private final UserRepository userRepository;

  private final AgentRepository agentRepository;

  private final PasswordEncoder passwordEncoder;

  private final AgentService agentService;

  private final SessionService sessionService;

  private final NotificationServiceHTTPClient notificationServiceHTTPClient;

  public UserService(UserRepository userRepository, AgentRepository agentRepository,
      PasswordEncoder passwordEncoder, AgentService agentService, SessionService sessionService,
      NotificationServiceHTTPClient notificationServiceHTTPClient) {
    this.userRepository = userRepository;
    this.agentRepository = agentRepository;
    this.passwordEncoder = passwordEncoder;
    this.agentService = agentService;
    this.sessionService = sessionService;
    this.notificationServiceHTTPClient = notificationServiceHTTPClient;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.contains("@")) {
      return userRepository.findByEmail(username)
          .orElseThrow(
              () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }
    return userRepository.findByMsisdn(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
  }

  @Transactional
  public User signUpUser(User user) {
    log.info("Signing up User: {}", user.getFullName());

    boolean userExists = userRepository.findByMsisdn(user.getMsisdn()).isPresent();

    if (userExists) {
      throw new IllegalStateException(String.format(USER_EXISTS, user.getMsisdn()));
    }

    if(user.getPassword() != null){
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
    }
    user.setEnabled(false);
    user.setCreatedAt(LocalDateTime.now());

    return userRepository.saveAndFlush(user);

  }

  public void enableAppUser(long userId) {

  //TODO: Implement this method
//    User user = userRepository.findUserById(userId)
//        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
//    user.setEnabled(true);
  }

  public Optional<User> findByEmail(String email) {
    log.info("Request to find user with email : {}", email);

    return userRepository.findByEmail(email);
  }
  public Optional<User> findByMsisdn(String msisdn) {
    log.info("Request to find user with phone : {}", msisdn);

    return userRepository.findByMsisdn(msisdn);
  }

  public Optional<User> findByUsername(String username){
    return userRepository.findUserByUsername(username);
  }


  public Optional<UserDTO> findById(Long userId) {
    return userRepository.findById(userId).map(
        currentUser-> userToUserDtoMapper().apply(currentUser)
    );

  }

  public void updateUser(User user) {
    log.info("Request to update user data for: {}", user.getFullName());
    Optional<User> optionalUser = userRepository.findByMsisdn(user.getMsisdn());

    if(optionalUser.isEmpty()){
      throw EntityNotFoundException.notFound("User not found");
    }
    userRepository.save(user);
    log.info("User Updated Successfully");
  }

  public UserDTO authenticate(String username){
    Optional<User> optionalUser = findByUsername(username);
    if (optionalUser.isEmpty()) {
      throw  EntityNotFoundException.notFound("Username is not registered");
    }

    User user = optionalUser.get();

    executorService.submit(()->sessionService.createLoginSession(user.getId()));

    UserDTO userDTO = userToUserDtoMapper().apply(user);
//    AgentDTO agentDTO = agentService.getAgentMetaData(userDTO.getAgent());
//    userDTO.setAgent(agentDTO);

    return userDTO;
  }

  @Transactional
  public void deleteUser(Long userId) {
    log.info("Request to delete user: {}", userId);

    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isEmpty()) {
      throw new IllegalStateException("User with id " + userId + " not found!");
    }

    sessionService.deleteByUserId(userId);
    userRepository.deleteById(userId);
  }
}
