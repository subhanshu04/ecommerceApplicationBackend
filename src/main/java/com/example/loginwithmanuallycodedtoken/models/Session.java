package com.example.loginwithmanuallycodedtoken.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sessionId;
    @OneToOne
    private User user;
    private String token;
}
