package com.example.loginwithmanuallycodedtoken.dtos;

import com.example.loginwithmanuallycodedtoken.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUserDTO {
    private Long userId;
    private String token;

}
