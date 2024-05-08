package com.example.campapp.controllers;

import com.example.campapp.dto.SignupRequest;
import com.example.campapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final AuthService authService;
@Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }
    @PatchMapping
    public ResponseEntity<String> signupUtilisateur(@RequestBody SignupRequest signupRequest){
        boolean isUserCreated = authService.createUtilisateur(signupRequest);
        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("faild to create user");
        }
    }
}
