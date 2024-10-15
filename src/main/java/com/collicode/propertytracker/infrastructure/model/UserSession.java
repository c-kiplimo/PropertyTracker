package com.collicode.propertytracker.infrastructure.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "tbl_user_sessions")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @JoinColumn(name = "user_id")
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
