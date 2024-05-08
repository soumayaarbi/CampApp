package com.example.campapp.services;

import com.example.campapp.dto.SignupRequest;

public interface AuthService {
    boolean createUtilisateur(SignupRequest signupRequest);
}
