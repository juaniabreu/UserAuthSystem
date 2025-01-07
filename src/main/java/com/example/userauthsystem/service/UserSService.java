package com.example.userauthsystem.service;

import com.example.userauthsystem.model.UserS;
import com.example.userauthsystem.repository.IUserSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserSService implements IUserSService {

    @Autowired
    private IUserSRepository userRepository;

    @Override
    public List<UserS> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserS> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserS save(UserS userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(UserS userSec) {
        save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
