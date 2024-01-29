package com.example.loginwithmanuallycodedtoken.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUserDTO {
    private Long userId;
    private String token;
}
