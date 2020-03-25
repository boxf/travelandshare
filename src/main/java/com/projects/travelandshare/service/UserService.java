package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.repository.UserRepository;
import com.projects.travelandshare.service.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

   private static UserRepository userRepository;

   @Autowired
   UserService (UserRepository userRepository){
       UserService.userRepository = userRepository;
   }

    /**
     * Used to register an user in the data base
     * @param user we want to register in the data base
     * @throws UserAlreadyExistException
     * If the user is already register with this email address
     * @author Marion Pradeau
     */
    public void userRegister (User user){
    Optional <User> userFound = userRepository.findUserByEmail(user.getEmail());
        if (!userFound.isPresent()){
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    /**
     * Used to login an user with is mail address
     * @param email from the user who want to be login
     * @throws UsernameNotFoundException
     * If the email doesn't exist in the data base
     * @author Marion Pradeau
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Objects.requireNonNull(email);
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return user;
    }

    public boolean checkEmailInDataBase (String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user == null){
            return false;
        } else {
            return true;
        }

    }
}
