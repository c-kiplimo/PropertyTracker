package com.collicode.propertytracker.service;

import static com.collicode.propertytracker.service.mapper.EntityToDtoMapper.agentToAgentDtoMapper;

import com.collicode.propertytracker.api.request.ForgotPassword;
import com.collicode.propertytracker.api.request.RegistrationRequest;
import com.collicode.propertytracker.config.Constants;
import com.collicode.propertytracker.exceptions.BadRequestException;
import com.collicode.propertytracker.infrastructure.model.Agent;
import com.collicode.propertytracker.infrastructure.model.ConfirmationToken;
import com.collicode.propertytracker.infrastructure.model.User;
import com.collicode.propertytracker.infrastructure.model.enums.AgentStatus;
import com.collicode.propertytracker.infrastructure.model.enums.UserRole;
import com.collicode.propertytracker.infrastructure.repository.UserRepository;
import com.collicode.propertytracker.notification.NotificationServiceHTTPClient;
import com.collicode.propertytracker.notification.emailtemplate.OTPUtil;
import com.collicode.propertytracker.service.dto.AgentDTO;
import com.collicode.propertytracker.service.dto.UserDTO;
import com.collicode.propertytracker.service.util.EmailValidatorUtil;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {
  private final static String EMAIL_NOT_VALID = "EMAIL %s IS NOT VALID";
  private final static String PHONE_NOT_VALID = "PHONE %s IS NOT VALID";
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserService userService;
  private  final AgentService agentService;
  private final ConfirmationTokenService confirmationTokenService;
  private final NotificationServiceHTTPClient notificationServiceHTTPClient;
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  @Transactional
  public void register(RegistrationRequest registrationRequest) {

    boolean isValidEmail = EmailValidatorUtil.validate(registrationRequest.getEmail());

    if (!isValidEmail) {
      throw new IllegalStateException(String.format(EMAIL_NOT_VALID, registrationRequest.getEmail()));
    }

    // Create User
    User user = userService.signUpUser(
        new User(registrationRequest.getFirstName(), registrationRequest.getLastName(), registrationRequest.getEmail(), registrationRequest.getPassword(), UserRole.ADMIN, registrationRequest.getMsisdn())
    );

    // Create Baker
    Agent agent = extractAgentFromRequest(registrationRequest);
    Agent savedAgent = agentService.addNewAgent(agent);
    agentService.save(agentToAgentDtoMapper().apply(savedAgent));


    log.info("Creating Default Settings for User");

    // Sending Confirmation Token
    log.info("Confirmation token for user| fullName: {} username: {}, id {} is about to be saved", user.getFullName(), user.getMsisdn(), user.getId());
    String token = generateAndSaveOTP(user.getId());
    sendConfirmationToken(token, "CONFIRM", user);
  }

  @Transactional
  public String generateAndSaveOTP(long userId) {
    // Generate a Random 6 digit OTP - 0 - 999999
    int randomOTP = (int) ((Math.random() * (999999 - 1)) + 1);
    String token = String.format("%06d", randomOTP);

    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(24 * 60 * 3), // 3 DAYS Expiry
        userId
    );

    confirmationTokenService.saveConfirmationToken(confirmationToken);

    return token;
  }

  private Agent extractAgentFromRequest(RegistrationRequest registrationRequest) {

    Agent agent = new Agent();
    agent.setFullName(registrationRequest.getFirstName() + ' ' + registrationRequest.getLastName());
    agent.setFirstName(registrationRequest.getFirstName());
    agent.setLastName(registrationRequest.getLastName());
    agent.setMsisdn(registrationRequest.getMsisdn());
    agent.setEmailAddress(registrationRequest.getEmail());
    agent.setStatus(AgentStatus.INCOMPLETE);
    agent.setRefId(registrationRequest.getReferralCode());
    agent.setOnboarded(false);

    return agent;
  }

  @Transactional
  public String confirmToken(String token, boolean isRegisterAccount) throws BadRequestException {
    log.info("Request to confirm OTP");
    ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
        new IllegalStateException("INVALID OTP!"));

    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("Phone Number Already Confirmed!");

    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Token Expired!");

    }

    confirmationTokenService.setConfirmedAt(token);

    userService.enableAppUser(confirmationToken.getUserId());


    // Update baker status and Send Welcome message and Send new user alert to support email
    if (isRegisterAccount) {
      log.info("About to complete registration process and send welcome SMS");
      completeRegistrationProcess(confirmationToken.getUserId());
    }

    return "CONFIRMED";

  }

  private void completeRegistrationProcess(Long userId) throws BadRequestException {
    Optional<UserDTO> userDTOOptional = userService.findById(userId);
    if (userDTOOptional.isEmpty()) {
      throw new BadRequestException("User with id: " + userId + " not found!");
    }

    UserDTO userDTO = userDTOOptional.get();
    AgentDTO agentDTO = userDTO.getAgent();
    agentDTO.setStatus(AgentStatus.NEW);
    agentService.save(agentDTO);

    log.info("Registration process completed");

  }



  public String reset(ForgotPassword forgotPassword) throws BadRequestException {
    log.info("Resetting Password");

    Optional<User> userOptional = userService.findByMsisdn(forgotPassword.getMsisdn());

    if (userOptional.isEmpty()) {
      throw new IllegalStateException(String.format(PHONE_NOT_VALID, forgotPassword.getMsisdn()));
    }
    User user = userOptional.get();

    // VERIFY TOKEN
    // TODO: HAVE A RESET PASSWORD TOKEN BE DIFF FROM ACCOUNT CREATION TOKEN.
    confirmToken(forgotPassword.getOtp(), false);
    // Add user
    String encodedPassword = passwordEncoder.encode(forgotPassword.getPassword());

    // Set details
    user.setPassword(encodedPassword);

    // save the User in the database
    userRepository.save(user);
    log.info("User Updated Successfully");

    return "PASSWORD CHANGED SUCCESSFULLY";
  }

  public String requestOTP(String msisdn, String action) {

    log.info("Generating OTP");

    Optional<User> userOptional = userService.findByMsisdn(msisdn);

    if (userOptional.isEmpty()) {
      throw new IllegalStateException(String.format(PHONE_NOT_VALID, msisdn));
    }
    User user = userOptional.get();
    // Generate a Random 6 digit OTP - 0 - 999999
    int randomOTP = (int) ((Math.random() * (999999 - 1)) + 1);
    String token = String.format("%06d", randomOTP);

    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(10), // Expires after 10 minutes
        user.getId()
    );

    confirmationTokenService.saveConfirmationToken(confirmationToken);
    log.info("Reset OTP generated");

    // Sending Confirmation OTP
    sendConfirmationToken(token, action, user);

    String email = user.getEmail() != null ? " and " + maskEmail(user.getEmail()) : "";

    return "OTP sent to " + maskMsisdn(msisdn) + email;
  }

  private String maskEmail(String email) {
    if (email == null || email.isEmpty()) {
      return null;
    }
    return email.replaceAll("(?<=..)[^@](?=[^@]*?@)", "*");
  }

  private String maskMsisdn(String customerMsisdn) {
    if (customerMsisdn == null || customerMsisdn.isEmpty()) {
      return null;
    }
    return customerMsisdn.replaceAll("(?<=....).(?=...)", "*");
  }

  void sendConfirmationToken(String token, String action, User user) {
    String title = "Complete Registration";
    String recipientName = user.getFirstName();
    String actionMessage = "complete registration";

    String smsContent = "Hello " + user.getFirstName() + ",\nUse this code to confirm your phone number: " + token + "\nYou can Ignore this If you didn't Initiate this action";
    if (Objects.equals(action, "RESET")) {
      title = "Reset Pin";
      actionMessage = "reset your password";

      smsContent = "Hello " + user.getFirstName() + ",\nUse this code to reset your password: " + token + "\nYou can Ignore this If you didn't Initiate this action";
    }

    // SHORTCODE
    String sourceAddress = Constants.DEFAULT_SMS_SOURCE_ADDRESS;
    String referenceId = UUID.randomUUID().toString();
    String msisdn = user.getUsername();
    String email = user.getEmail();


    notificationServiceHTTPClient.sendSMS(sourceAddress, msisdn, smsContent, referenceId);

    String emailBody = OTPUtil.getOTPEmailBody(title, recipientName, actionMessage, token);
    notificationServiceHTTPClient.sendEmail(email, title, emailBody, true);
  }


}
