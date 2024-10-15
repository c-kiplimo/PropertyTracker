package com.collicode.propertytracker.api;

import com.collicode.propertytracker.api.request.Login;
import com.collicode.propertytracker.api.request.LoginToken;
import com.collicode.propertytracker.security.JwtTokenUtil;
import com.collicode.propertytracker.service.dto.UserDTO;
import com.collicode.propertytracker.service.SessionService;
import com.collicode.propertytracker.service.UserService;
import com.collicode.propertytracker.util.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@Slf4j
public class AuthenticationApiController {
  private final UserService userService;

  private final AuthenticationManager authenticationManager;

  private final SessionService sessionService;

  private final JwtTokenUtil jwtTokenUtil;


  public AuthenticationApiController(UserService userService,
      AuthenticationManager authenticationManager, SessionService sessionService,
      JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.sessionService = sessionService;
    this.jwtTokenUtil = jwtTokenUtil;
  }


  @PostMapping
  public ResponseEntity<?> generateToken(@RequestBody Login login) {
    try {

      log.info("REST request to login user: {}", login.getUsername());

      UserDTO userDTO = userService.authenticate(login.getUsername());

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenUtil.generateToken(userDTO, login.getRememberMe());


      return new ResponseEntity<>(new LoginToken(userDTO, token), HttpStatus.OK);

    } catch (AuthenticationException auth){
      log.error("Authentication error for  {} Ex: {}", login.getUsername(), auth.getMessage());
      return new ResponseEntity<>(new RestResponse(true, "Wrong Username/Password."), HttpStatus.UNAUTHORIZED);
    } catch (Exception e){
      log.error("Error occurred while calling generateToken ", e);
      return new ResponseEntity<>(new RestResponse(true, "Error occurred, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/logout/{token}")
  public ResponseEntity<?> logout(@PathVariable("token") String token) {
    try{
      sessionService.updateSessionOnLogOut();
      return new ResponseEntity<>(new RestResponse(false, "User logged out"), HttpStatus.OK);
    }catch (Exception e){
      log.error("Error occurred while calling {} for {} Ex: {}", "logout", token, e);
      return new ResponseEntity<>(new RestResponse(true, "Failed to Logout, Try Later"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
