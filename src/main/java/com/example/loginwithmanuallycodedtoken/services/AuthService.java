package com.example.loginwithmanuallycodedtoken.services;

import com.example.loginwithmanuallycodedtoken.ValidateUserDto;
import com.example.loginwithmanuallycodedtoken.dtos.UserDTO;
import com.example.loginwithmanuallycodedtoken.dtos.UserLoginDTO;
import com.example.loginwithmanuallycodedtoken.exceptions.UserDoesNotExistException;
import com.example.loginwithmanuallycodedtoken.exceptions.WrongPasswordException;
import com.example.loginwithmanuallycodedtoken.models.Session;
import com.example.loginwithmanuallycodedtoken.models.SessionStatus;
import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.repositories.SessionRepo;
import com.example.loginwithmanuallycodedtoken.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {
    private UserRepo userRepo;
    private SessionRepo sessionRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepo userRepo, SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
    }

    public void signUp(UserLoginDTO userLoginDTO){
        if(userRepo.findByUsername(userLoginDTO.getUsername()).isPresent()){
            //throw User already present exception.
        }
        //Bcrypt pass using Hashing and Salt
        User user = new User();
        user.setUsername(userLoginDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userLoginDTO.getPassword()));
        userRepo.save(user);
    }
    public ResponseEntity<UserDTO> login(UserLoginDTO userLoginDto) throws UserDoesNotExistException, WrongPasswordException {
        Optional<User> userOptional = userRepo.findByUsername(userLoginDto.getUsername());
        if(userOptional.isEmpty()){
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();;
        if(!bCryptPasswordEncoder.matches(userLoginDto.getPassword(),user.getPassword())){
            throw new WrongPasswordException();
        }
        String token = RandomStringUtils.randomAscii(20);

        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepo.save(session);

        UserDTO userDTO = UserDTO.from(user);
        MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(
                "AUTH_TOKEN",token
        );
        return new ResponseEntity<>(userDTO,headers, HttpStatus.OK);
    }

    public String validateUser(Long userId,String token){
        Session session = sessionRepo.findByUser_Id(userId).get();
        if(session.getToken().equals(token)){
            return "user is valid";
        }
        return "User invalid";
    }

}
