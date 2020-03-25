package com.projects.travelandshare.security;

import com.projects.travelandshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class AppAuthProvider extends DaoAuthenticationProvider {
    @Autowired
    UserService userService;

    public Authentication authenticate (Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth=(UsernamePasswordAuthenticationToken)authentication;

        String email = auth.getName();
        String password = auth.getCredentials().toString();

        UserDetails user = userService.loadUserByUsername(email);

        if(user.getUsername().equals(email) && user.getPassword().equals(password)){
            return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
        }
        throw new BadCredentialsException("Username/Password does not match for" + auth.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
