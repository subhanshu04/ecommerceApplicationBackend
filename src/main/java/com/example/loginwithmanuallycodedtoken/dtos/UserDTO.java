package com.example.loginwithmanuallycodedtoken.dtos;

import com.example.loginwithmanuallycodedtoken.models.Role;
import com.example.loginwithmanuallycodedtoken.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String username;
    private Set<Role> roles = new HashSet<>();

    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
