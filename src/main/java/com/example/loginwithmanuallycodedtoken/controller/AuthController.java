package com.example.loginwithmanuallycodedtoken.controller;

import com.example.loginwithmanuallycodedtoken.ValidateUserDto;
import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public void login(@RequestBody User user){
        System.out.println(user.getUsername());
        authService.login(user);
    }
    @PostMapping("/validate")
    public String validate(@RequestBody ValidateUserDto validateUserDto){
        return authService.validate(validateUserDto);
    }
}
