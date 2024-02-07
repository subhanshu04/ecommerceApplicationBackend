package com.example.loginwithmanuallycodedtoken.security.models;

import com.example.loginwithmanuallycodedtoken.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;
    private String authority;

    public CustomGrantedAuthority(Role role){
        this.role=role;
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
