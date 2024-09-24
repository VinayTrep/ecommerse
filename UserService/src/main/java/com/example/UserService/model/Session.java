package com.example.UserService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "ecom_user_sessions")
public class Session extends BaseModel{
    @Column(length = 500)
    private String token;
    private Instant expiresAt;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private SessionType sessionType;
}
