package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;

public interface UserServiceDao {

    //service for registering new user
    public User registerUser(User user) throws UserAlreadyExistsException;

    //service for validating the user
    boolean validateUser(User user) throws UserNotFoundException;


    public User getUserRoleByMailId(String mailId) throws UserNotFoundException;

    public User getUserByMail(String mailId) throws UserNotFoundException;
}
