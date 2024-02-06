package com.example.loginwithmanuallycodedtoken.security.services;

import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.repositories.UserRepo;
import com.example.loginwithmanuallycodedtoken.security.models.CustomeUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // This method needs an object of UserDetails. So lets create a class that will implement userDetails and we can create its object.
    //This method is needed so that the spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not present");
        }
        //We are creating an object of CustomeUserDetails which implements UserDetails.
        return new CustomeUserDetails(optionalUser.get());
    }
}
