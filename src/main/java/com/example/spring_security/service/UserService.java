package com.example.spring_security.service;

import com.example.spring_security.entitty.UserSec;
import com.example.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService =jwtService;
    }

    public UserSec register(UserSec userSec) {

        userSec.setPassword(bCryptPasswordEncoder.encode(userSec.getPassword()));
        userSec.setPassword(userSec.getPassword());

        return userRepo.save(userSec);
    }

    public String verify(UserSec userSec) {

//        return userRepo.findByUsername(userSec.getUsername());

//        var u = userRepo.findByUsername(userSec.getUsername());
//        if (!Objects.isNull(u)) {
//            return "success";
//        }
//
//        return "failure";

        Authentication authenticate
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userSec.getUsername(), userSec.getPassword()
                )
        );

        
        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(userSec);
//            return "success";
        }
        return "failure";


    }


}
