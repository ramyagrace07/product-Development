package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.AddressIdNotFoundException;
import com.stackroute.authenticationservice.model.Address;
import com.stackroute.authenticationservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressServiceDao{
    @Autowired
    AddressRepository repository;

    @Override
    public Address addAddress(Address address) {
        return repository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return repository.findAll();
    }

    @Override
    public List<Address> findAddressByEmailId(String emailId) {
        return repository.findByEmailId(emailId);
    }

    @Override
    public boolean deleteById(int addId) throws AddressIdNotFoundException {
        Optional<Address> product=repository.findById(addId);
        if(product.isEmpty()){
            throw  new AddressIdNotFoundException("product ID doesn't exist");
        }else{
            repository.deleteById(addId);
            return true;
        }
    }
}