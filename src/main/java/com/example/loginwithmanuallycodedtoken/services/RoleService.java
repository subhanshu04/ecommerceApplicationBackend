package com.example.loginwithmanuallycodedtoken.services;

import com.example.loginwithmanuallycodedtoken.models.Role;
import com.example.loginwithmanuallycodedtoken.repositories.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role createNewRole(Role role){
        return roleRepo.save(role);
    }
}
