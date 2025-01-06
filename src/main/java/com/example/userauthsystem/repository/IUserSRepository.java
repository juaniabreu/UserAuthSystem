package com.example.userauthsystem.repository;

import com.example.userauthsystem.model.UserS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSRepository extends JpaRepository<UserS, Long> {

    Optional<UserS> findUserEntityByUsername(String username);

}
