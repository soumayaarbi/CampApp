package com.example.campapp.controllers;

import com.example.campapp.dto.AuthenticationResponse;
import com.example.campapp.dto.ResetPasswordRequest;
import com.example.campapp.entities.User;
import com.example.campapp.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PutMapping("/updateProfile")
    public ResponseEntity<AuthenticationResponse> updateProfile(@RequestBody User request) {
        return ResponseEntity.ok(authService.updateUserProfile(request));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean result = authService.resetPassword(request.getUsername(), request.getNewPassword());
        if (result) {
            return ResponseEntity.ok("Password successfully reset");
        } else {

            return ResponseEntity.ok("Password reset failed");
        }
    }


}
