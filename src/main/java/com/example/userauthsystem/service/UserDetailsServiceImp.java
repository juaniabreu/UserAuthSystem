package com.example.userauthsystem.service;

import com.example.userauthsystem.model.UserS;
import com.example.userauthsystem.repository.IUserSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    public UserDetailsServiceImp() {

    }
    @Autowired
    IUserSRepository userSRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserS user = (UserS)this.userSRepository.findUserEntityByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + "no fue encontrado")
        );

        List<GrantedAuthority> authorityList = new ArrayList<>();

        user.getRolesList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role)));
        user.getRolesList().stream()
                .flatMap(role -> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));
        return new User(user.getUsername(),
                user.getPassword(),user.isEnabled(),
                user.isAccountNotExpired(),
                user.isCredentialNotExpired(),
                user.isAccountNotLocked(),
                authorityList);
    }
}
