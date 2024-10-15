package com.collicode.propertytracker.service;

import com.collicode.propertytracker.infrastructure.model.UserSession;
import com.collicode.propertytracker.infrastructure.repository.UserSessionRepository;
import com.collicode.propertytracker.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SessionService {
  private final UserSessionRepository userSessionRepo;
  private final UserSessionRepository userSessionRepository;

  public SessionService(UserSessionRepository userSessionRepo,
      UserSessionRepository userSessionRepository) {
    this.userSessionRepo = userSessionRepo;
    this.userSessionRepository = userSessionRepository;
  }

  public void createLoginSession(long userId){
    UserSession userSession = findByUserId(userId);
    if(userSession ==  null){
      userSession = new UserSession();
      userSession.setUserId(userId);
      userSession.setLoggedIn(1);
    } else {
      userSession.setLoginTrials(userSession.getLoginTrials()+1);
    }
    userSessionRepo.save(userSession);
  }

  public void updateSessionOnLogOut(){
    SecurityUtils.getCurrentUser().ifPresent(user -> {
      UserSession userSession = findByUserId(user.getId());
      userSession.setLoggedIn(0);
      userSessionRepo.save(userSession);
    });
  }

  public UserSession findByUserId(Long userId){
    log.info("Request to get session by userId: {}", userId);
    return userSessionRepo.findByUserId(userId);
  }

  public void deleteByUserId(Long userId){
    userSessionRepository.deleteByUserId(userId);
  }
}
