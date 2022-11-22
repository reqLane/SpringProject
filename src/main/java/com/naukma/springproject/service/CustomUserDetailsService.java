package com.naukma.springproject.service;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.model.CustomUserDetails;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = repo.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given login");
        }
        return new CustomUserDetails(user);
    }
}
