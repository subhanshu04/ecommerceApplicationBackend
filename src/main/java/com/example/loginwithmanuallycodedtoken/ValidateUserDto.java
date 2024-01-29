package com.example.loginwithmanuallycodedtoken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUserDto {
    private String username;
    private String token;
}
