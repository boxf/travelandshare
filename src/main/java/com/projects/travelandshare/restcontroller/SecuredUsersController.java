package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.security.UserAuthenticationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class SecuredUsersController {
    UserAuthenticationService authentication;

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final User user){
        authentication.logout(user);
        return true;
    }
}
