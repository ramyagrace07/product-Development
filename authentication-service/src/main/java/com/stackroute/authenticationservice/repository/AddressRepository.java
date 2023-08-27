package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByEmailId(String emailId);
}
