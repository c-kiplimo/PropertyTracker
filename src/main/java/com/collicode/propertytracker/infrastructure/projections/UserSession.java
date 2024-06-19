package com.collicode.propertytracker.infrastructure.projections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "tbl_user_sessions")
public class UserSession {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long userId;
  @Column(name = "logged_in", nullable = false)
  private int loggedIn;
  @Column(name = "login_trials", nullable = false)
  private int loginTrials;
  @CreationTimestamp
  @Column(name = "created_at")
  private Timestamp createdAt;
  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;
}
