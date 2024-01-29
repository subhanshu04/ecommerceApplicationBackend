package com.example.loginwithmanuallycodedtoken.services;

import com.example.loginwithmanuallycodedtoken.ValidateUserDto;
import com.example.loginwithmanuallycodedtoken.models.Session;
import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.repositories.SessionRepo;
import com.example.loginwithmanuallycodedtoken.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    private UserRepo userRepo;
    private SessionRepo sessionRepo;

    public AuthService(UserRepo userRepo, SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
    }

    public void login(User user){
        userRepo.save(user);
        String token = generateRandomToken(20);
        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        sessionRepo.save(session);
    }

    @Transactional
    public String validate(ValidateUserDto validateUserDto){
        Session session = sessionRepo.findByToken(validateUserDto.getToken()).get();
        if(session.getUser().getUsername().equals(validateUserDto.getUsername())){
            sessionRepo.deleteByToken(validateUserDto.getToken());
            return "User is valid";
        }
        return "User is invalid";
    }
    private static String generateRandomToken(int length) {
        String characters = "0123456789";
        StringBuilder tokenBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            tokenBuilder.append(randomChar);
        }
        return tokenBuilder.toString();
    }
}
