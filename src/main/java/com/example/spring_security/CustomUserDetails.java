package com.example.spring_security;

import com.example.spring_security.entitty.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {


    private final UserSec userSec;

    public CustomUserDetails(UserSec userSec) {
        this.userSec = userSec;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();

        return Collections.singleton(new SimpleGrantedAuthority("USER"));

//        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return userSec.getPassword();
    }

    @Override
    public String getUsername() {
        return userSec.getUsername();
    }
}
