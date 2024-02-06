package com.example.loginwithmanuallycodedtoken.controller;

import com.example.loginwithmanuallycodedtoken.dtos.UserDTO;
import com.example.loginwithmanuallycodedtoken.dtos.UserLoginDTO;
import com.example.loginwithmanuallycodedtoken.dtos.ValidateUserDTO;
import com.example.loginwithmanuallycodedtoken.exceptions.UserDoesNotExistException;
import com.example.loginwithmanuallycodedtoken.exceptions.WrongPasswordException;
import com.example.loginwithmanuallycodedtoken.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserLoginDTO userLoginDTO){
        authService.signUp(userLoginDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO userLoginDto) throws UserDoesNotExistException, WrongPasswordException {
        return authService.login(userLoginDto);
    }
    @PostMapping("/validate")
    public ResponseEntity<ValidateUserDTO> validate(@RequestHeader("AUTH_TOKEN") String token ){
        return authService.validateUser(token);
    }
}
