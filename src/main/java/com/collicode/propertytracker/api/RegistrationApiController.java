package com.collicode.propertytracker.api;

import com.collicode.propertytracker.api.request.ForgotPassword;
import com.collicode.propertytracker.api.request.RegistrationRequest;
import com.collicode.propertytracker.service.RegistrationService;
import com.collicode.propertytracker.util.RestResponse;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationApiController {

        private final RegistrationService registrationService;


        @PostMapping
        public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
            try{
                registrationService.register(registrationRequest);
                return ResponseEntity.created(new URI("/api/v1/registration")).body(new RestResponse(false,"User registered Successfully!"));

            } catch (Exception e){
                log.info("Error: ", e);
                return new ResponseEntity<>(new RestResponse(true,e.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping(path = "confirm")
        public ResponseEntity<?> confirm(@RequestParam("token") String token){
            log.info("REST Request to confirm OTP");

            try{
                String response = registrationService.confirmToken(token, true);
                return new ResponseEntity<>(new RestResponse(false,response), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(new RestResponse(true,e.getMessage()),
                        HttpStatus.OK);
            }
        }
    @GetMapping(path = "reset")
    public ResponseEntity<?> reset(@RequestParam("msisdn") String msisdn) {
        try{
            String response =registrationService.requestOTP(msisdn,"RESET");
            return new ResponseEntity<>(new RestResponse(false,response),
                    HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse(true,e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "resend")
    public ResponseEntity<?> resend(@RequestParam("msisdn") String msisdn) {
        try{
            String response =registrationService.requestOTP(msisdn,"RESEND");
            return new ResponseEntity<>(new RestResponse(false,response),
                    HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse(true,e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "forgot")
    public ResponseEntity<?> forgot(@RequestBody ForgotPassword forgotPassword) {
        try{
            String response =registrationService.reset(forgotPassword);
            return new ResponseEntity<>(new RestResponse(false,response),
                    HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse(true,e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
