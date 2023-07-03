package com.railwayreservation.userlogin.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.railwayreservation.userlogin.model.UserCredential;

import java.util.List;
import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByEmail(String email);
    
    
     //public List<UserCredential> findById(int id)
    
   
}
