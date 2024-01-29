package com.example.loginwithmanuallycodedtoken.controller;

import com.example.loginwithmanuallycodedtoken.models.Role;
import com.example.loginwithmanuallycodedtoken.services.RoleService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<String> createNewRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.createNewRole(role).getName(), HttpStatus.OK);
    }
}
