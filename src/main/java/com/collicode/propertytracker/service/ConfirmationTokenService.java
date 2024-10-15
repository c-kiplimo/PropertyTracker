package com.collicode.propertytracker.service;

import com.collicode.propertytracker.infrastructure.model.ConfirmationToken;
import com.collicode.propertytracker.infrastructure.repository.ConfirmationTokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {
  private final ConfirmationTokenRepository confirmationTokenRepository;
  private final Logger logger = LoggerFactory.getLogger(ConfirmationTokenService.class);


  public void saveConfirmationToken(ConfirmationToken confirmationToken){
    logger.info("Saving Confirmation Token : {}", confirmationToken);
    confirmationTokenRepository.save(confirmationToken);
  }


  public Optional<ConfirmationToken> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }


  public void setConfirmedAt(String token) {
    ConfirmationToken  confirmedUser = confirmationTokenRepository.findByToken(token).orElseThrow(()->
        new IllegalStateException("Specified Token Not Found!"));

    confirmedUser.setConfirmedAt(LocalDateTime.now()); // TODO: Verify if this affects the dB

  }
}
