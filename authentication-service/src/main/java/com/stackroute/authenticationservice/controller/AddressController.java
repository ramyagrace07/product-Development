package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.exception.AddressIdNotFoundException;
import com.stackroute.authenticationservice.model.Address;
import com.stackroute.authenticationservice.service.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
@CrossOrigin("*")
public class AddressController {
    @Autowired
    AddressServiceImpl service;

    @PostMapping("addaddress")
    public ResponseEntity<?> addAddress(@RequestBody Address address){
        return new ResponseEntity<Address>(service.addAddress(address),HttpStatus.CREATED);
    }

    @GetMapping("viewall")
    public ResponseEntity<?> getAllAddresses(){
        List<Address> addressList = service.getAllAddresses();
        return new ResponseEntity<List>(addressList, HttpStatus.OK);
    }

    @GetMapping("a/{emailId}")
    public ResponseEntity<?> viewAddressByEmailId(@PathVariable String emailId){
        List<Address> addressList = service.findAddressByEmailId(emailId);
        return new ResponseEntity<List>(addressList, HttpStatus.OK);
    }

    @DeleteMapping("deleteAddressById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int addId){
        try{
            Boolean result = service.deleteById(addId);
            return new ResponseEntity<String >("Address is deleted", HttpStatus.OK);
        }catch (AddressIdNotFoundException e){
            return new ResponseEntity<String>("Address id is not exist in database", HttpStatus.CONFLICT);
        }

    }

}
