package com.example.spring_security.controller;

import com.example.spring_security.entitty.UserSec;
import com.example.spring_security.repo.UserRepo;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<UserSec> register(@RequestBody UserSec userSec){

        return new ResponseEntity<>(userService.register(userSec), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public String login(@RequestBody UserSec userSec){

       return userService.verify(userSec);

    }

}
