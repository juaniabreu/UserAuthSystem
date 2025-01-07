package com.example.userauthsystem.controller;

import com.example.userauthsystem.model.Role;
import com.example.userauthsystem.model.UserS;
import com.example.userauthsystem.service.IRoleService;
import com.example.userauthsystem.service.IUserSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserSController {

    @Autowired
    private IUserSService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserS>> getAllUsers() {
        List<UserS> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserS> getUserById(@PathVariable Long id) {
        Optional<UserS> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserS> createUser(@RequestBody UserS userSec) {

        Set<Role> roleList = new HashSet<>();
        Role readRole;
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));
        // Recuperar la Permission/s por su ID
        for (Role role : userSec.getRolesList()){
            readRole = roleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                //si encuentro, guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            userSec.setRolesList(roleList);

            UserS newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }
}
