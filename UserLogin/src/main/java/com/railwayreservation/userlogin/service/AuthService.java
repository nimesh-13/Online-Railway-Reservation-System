package com.railwayreservation.userlogin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.railwayreservation.userlogin.exception.UseralreadyExistException;
import com.railwayreservation.userlogin.model.UserCredential;
import com.railwayreservation.userlogin.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
         
		 credential.setPassword(passwordEncoder.encode(credential.getPassword()));
	     credential.setRole("User");
         repository.save(credential);
         return "user added to the system";
    }
    

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

	public List<UserCredential> getAll() {
		return repository.findAll();
	}


}
