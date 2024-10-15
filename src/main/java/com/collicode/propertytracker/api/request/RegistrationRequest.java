package com.collicode.propertytracker.api.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
  private long id;
  private String firstName;
  private String lastName;
  private String msisdn;
  private final String email;
  private String password;
  private String referralCode;

}
