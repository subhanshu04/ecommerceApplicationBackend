package com.example.loginwithmanuallycodedtoken.controller;

import com.example.loginwithmanuallycodedtoken.exceptions.UserDoesNotExistException;
import com.example.loginwithmanuallycodedtoken.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> userNotExistExceptionHandler(){
        return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> wrongPasswordExceptionHandler(){
        return new ResponseEntity<>("Wrong password. Please check", HttpStatus.BAD_REQUEST);
    }
}
