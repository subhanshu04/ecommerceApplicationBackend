package com.example.loginwithmanuallycodedtoken.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private Date expiringAt;
    @ManyToOne
    private User user;
    private String token;
    private SessionStatus sessionStatus;
}
