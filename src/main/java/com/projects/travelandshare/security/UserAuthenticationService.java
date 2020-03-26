package com.projects.travelandshare.security;

import com.projects.travelandshare.model.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserAuthenticationService {

    Optional<String> login(String username, String password);

    Optional<User> findBuToken(String Token);

    void logout(User user);
}
