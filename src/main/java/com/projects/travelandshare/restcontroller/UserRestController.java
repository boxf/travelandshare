package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.User;
import com.projects.travelandshare.service.UserService;
import com.projects.travelandshare.service.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RestController for User. It permit to get back the information in the model and communicate with the view
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserRestController {
    /**
     * Dependencies injection of UserService
     */
    @Autowired
    UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method for register a new user from the html to the data base
     * @param user we want to register in the data base
     * @throws UserAlreadyExistException
     * If the user is already register with this email address
     * @author Marion Pradeau
     */
    @PostMapping(value = "/user", consumes ="multipart/form-data")
    public ResponseEntity<Object> addNewUser(@ModelAttribute User user){
        try{
            userService.userRegister(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistException exist){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Receives the request from Angular to check if the email already exists in the database
     * @param email is apssed as the parameter to check in the database
     * @return passes the email to the userService
     */
    @RequestMapping ("/validateEmail/{email}")
    public boolean checkEmailExists (@PathVariable("email") String email){
        return this.userService.checkEmailInDataBase(email);
    }
    @GetMapping(produces = "application/json")
    @RequestMapping({"/login"})
    public String validateLogin(){
        return "User successfully authenticated";
    }
}
