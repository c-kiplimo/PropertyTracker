package com.collicode.propertytracker.infrastructure.projections;

import com.collicode.propertytracker.infrastructure.projections.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_users")
public class User implements UserDetails {

  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
  @Id
  private long id;
  private String firstName;
  private String lastName;
  private String fullName;
  private String email;
  private String msisdn;
  private String username;
  private String password;
  private String activationKey;
  private LocalDateTime activationKeyExpiryDate;
  private Boolean showImportantInfo;


  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;


  @Enumerated(EnumType.STRING) //
  private UserRole userRole;

  private long agentCode;

  private Boolean locked = false;
  private Boolean enabled = false;

  public User(String firstName,String lastName, String email, String password,
      UserRole appUserRole, String msisdn) {
    this.fullName = firstName + ' '+lastName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = appUserRole;
    this.msisdn = msisdn;
    this.username = msisdn;
  }

  public User(String firstName,String lastName, String email, String password,
      UserRole appUserRole, String msisdn, String activationKey,
      LocalDateTime activationKeyExpiryDate, long agentCode) {
    this.fullName = firstName + ' '+lastName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = appUserRole;
    this.msisdn = msisdn;
    this.username = msisdn;
    this.activationKey = activationKey;
    this.activationKeyExpiryDate = activationKeyExpiryDate;
    this.agentCode = agentCode;

  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.name());

    return Collections.singletonList(grantedAuthority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return msisdn;
  }

  public String getFullName() {
    return fullName;
  }




  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }


}
