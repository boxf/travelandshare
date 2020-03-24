package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.repository.UserRepository;
import com.projects.travelandshare.service.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * Used to register an user in the data base
     * @param user we want to register in the data base
     * @throws UserAlreadyExistException
     * If the user is already register with this email address
     * @author Marion Pradeau
     */
    public void userRegister (User user){
        User userFound = userRepository.findUserByEmail(user.getEmail());
        if (userFound == null){
            this.userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }
    }
}
