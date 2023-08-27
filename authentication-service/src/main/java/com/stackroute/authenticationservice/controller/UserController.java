package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImpl service;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user)throws UserAlreadyExistsException{
//        return new ResponseEntity<User>(service.registerUser(user), HttpStatus.CREATED);
        try {
            User user1 = service.registerUser(user);
            return new ResponseEntity<User>(user1, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<String>("User Already Exists in Database", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException{
        try {
            boolean result = service.validateUser(user);
            if(result){
                String  token = generateToken(user);
                HashMap hashMap = new HashMap();
                hashMap.put("token",token);
                return new ResponseEntity<HashMap>(hashMap,HttpStatus.OK);

            }
            else {
                return new ResponseEntity<String>("Invalid Credentails", HttpStatus.UNAUTHORIZED);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<String>("User not found in the database", HttpStatus.CONFLICT);
        }

    }

    private String generateToken(User user){
        return Jwts.builder().setSubject(user.getFirstName())
                .setAudience(user.getMailId())
                .setAudience("ramya")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+30000))
                .signWith(SignatureAlgorithm.HS256,"emartHub").compact();
    }

    @GetMapping("userdetails/{mail}")
    public ResponseEntity<?> getUserByMail(@PathVariable String mail) throws UserNotFoundException {
//        User user = service.getUserByMail(mail);
//        return new ResponseEntity<User>(user, HttpStatus.OK);
        try {
            User user = service.getUserByMail(mail);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("role/{mail}")
    public ResponseEntity<String> getRoleByMailId(@PathVariable String mail) throws UserNotFoundException {
        User user = service.getUserRoleByMailId(mail);
        if(user == null){
            return new ResponseEntity<String>("Record not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user.getRole(), HttpStatus.OK);
    }

}
