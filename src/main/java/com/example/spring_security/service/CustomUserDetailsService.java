package com.example.spring_security.service;

import com.example.spring_security.CustomUserDetails;
import com.example.spring_security.entitty.UserSec;
import com.example.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserSec userR = userRepo.findByUsername(username);

//        if(Objects.isNull(userR)) {
//            System.out.println("User not available");
//            throw new UsernameNotFoundException("User not found");
//        }

        if (userR == null) {

            throw new UsernameNotFoundException("User not found");
        }
//       System.out.println("User not available");

        return new CustomUserDetails(userR);

    }
}
