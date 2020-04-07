package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.repository.UserRepository;

import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){

    }

    @Test
    void userRegister() {
        User user = new User ("Pellegrini", "Cedric", "coucou@coucou.fr", "cestlafete");
        when(userRepository.save(user)).thenReturn(user);
        userRepository.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void loadUserByUsername() {
        String userName = "coucou@coucou.fr";
        when(userRepository.findUserByEmail(userName))
                .thenReturn(Optional.of(new User ("Pellegrini", "Cedric", "coucou@coucou.fr", "cestlafete")));
        assertEquals(userName, userService.loadUserByUsername(userName).getUsername());
    }

    @Test
    void checkEmailInDataBase() {
        String userName = "coucou@coucou.fr";
        when(userRepository.findUserByEmail(userName))
                .thenReturn(Optional.of(new User ("Pellegrini", "Cedric", "coucou@coucou.fr", "cestlafete")));
        assertTrue(userService.checkEmailInDataBase(userName));
        assertFalse(userService.checkEmailInDataBase("coucou123@coucou.fr"));
    }

}