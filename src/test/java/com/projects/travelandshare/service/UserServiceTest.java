package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.repository.UserRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.service.exception.UserAlreadyExistException;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void givenNewUser_whenUserDoesntExist_thenRegisterUser() {
        //Given
        User user = new User("Marion", "Pradeau", "marion.pradeau@wanadoo.fr", "azerty123");
        when(userRepository.save(user)).thenReturn(user);

        //When
        userService.userRegister(user);

        //Then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void givenNewUser_whenUserExist_thenThrowsException() {
        //Given
        User mockedUser = new User("Marion", "Pradeau", "marion.pradeau@wanadoo.fr", "azerty123");
        when(userRepository.findUserByEmail("marion.pradeau@wanadoo.fr")).thenReturn(java.util.Optional.of(mockedUser));

        //When-Then
        Assertions.assertThrows(UserAlreadyExistException.class, () -> {
            User user2 = new User("Marion", "Pradeau", "marion.pradeau@wanadoo.fr", "azerty123");
            userService.userRegister(user2);
        });
    }
    @Test
    void loadUserByUsername() {
    }
}