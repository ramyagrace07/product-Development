package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserServiceDao{
    @Autowired
    UserRepository repository;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException{
        Optional<User> optionalUser = Optional.ofNullable(repository.findByMailId(user.getMailId()));
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("Duplicate email id found");
        }
        else {
            User user1 = repository.save(user);
            return user1;
        }
//        return repository.save(user);
    }

    @Override
    public boolean validateUser(User user) throws UserNotFoundException {
        Optional<User> user1 = Optional.ofNullable(repository.findByMailIdAndPassword(user.getMailId(), user.getPassword()));
        if (user1.isEmpty()){
            throw new UserNotFoundException("User is not found in the database");
        }
        else {
            return true;
        }
    }

    @Override
    public User getUserRoleByMailId(String mailId) throws UserNotFoundException{
        Optional<User> optionalUser = Optional.ofNullable(repository.findByMailId(mailId));
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Email id not found");
        }
        else {
            User user1 = repository.findByMailId(mailId);
            return user1;
        }
//        return repository.findByMailId(mailId);
    }

    @Override
    public User getUserByMail(String mailId) throws UserNotFoundException{
        Optional<User> optionalUser = Optional.ofNullable(repository.findByMailId(mailId));
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Email id not found");
        }
        else {
            User user1 = repository.findByMailId(mailId);
            return user1;
        }
//        return repository.findByMailId(mailId);
    }
}

