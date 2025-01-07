package com.example.userauthsystem.service;

import com.example.userauthsystem.model.UserS;

import java.util.List;
import java.util.Optional;


public interface IUserSService {

    public List<UserS> findAll();
    public Optional<UserS> findById(Long id);
    public UserS save(UserS userSec);
    public void deleteById(Long id);
    public void update(UserS userSec);
    public String encriptPassword(String password);

}

