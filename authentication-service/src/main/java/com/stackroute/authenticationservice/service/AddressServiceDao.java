package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.AddressIdNotFoundException;
import com.stackroute.authenticationservice.model.Address;

import java.util.List;

public interface AddressServiceDao {
    Address addAddress(Address address);
    List<Address> getAllAddresses();
    List<Address> findAddressByEmailId(String emailId);
    public boolean deleteById(int addId) throws AddressIdNotFoundException;


}
