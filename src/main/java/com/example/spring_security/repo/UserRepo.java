package com.example.spring_security.repo;


import com.example.spring_security.entitty.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<UserSec,Long> {


    UserSec findByUsername(String username);
}
