package com.example.loginwithmanuallycodedtoken.controller;

import com.example.loginwithmanuallycodedtoken.dtos.RoleDTO;
import com.example.loginwithmanuallycodedtoken.dtos.UserDTO;
import com.example.loginwithmanuallycodedtoken.models.User;
import com.example.loginwithmanuallycodedtoken.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserDetails(@PathVariable long id){
        User user = userService.getUserDetails(id);
        UserDTO response = UserDTO.from(user);
        return response;
    }
    @PostMapping("/{id}")
    public UserDTO addRoles(@PathVariable long id, @RequestBody RoleDTO roleDTO){
        User user = userService.addRoles(id,roleDTO);
        return UserDTO.from(user);
    }
}
