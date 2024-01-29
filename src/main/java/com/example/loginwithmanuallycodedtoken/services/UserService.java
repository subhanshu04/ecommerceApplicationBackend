package com.example.loginwithmanuallycodedtoken.services;

import com.example.loginwithmanuallycodedtoken.dtos.RoleDTO;
import com.example.loginwithmanuallycodedtoken.models.Role;
import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User getUserDetails(Long userId){
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isEmpty()){
            //throw exception
        }
        return optionalUser.get();
    }

    public User addRoles(Long id, RoleDTO roleDTO){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isEmpty()){
            //Throw exception
        }
        User user = optionalUser.get();

        Role role = new Role();
        role.setName(roleDTO.getName());

        user.getRoles().add(role);

        userRepo.save(user);
        return user;
    }
}
