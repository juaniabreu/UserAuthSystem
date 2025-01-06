package com.example.userauthsystem.service;

import com.example.userauthsystem.model.Role;

import java.util.List;
import java.util.Optional;


public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    Role update(Role role);
}
