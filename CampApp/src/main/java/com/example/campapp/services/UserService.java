package com.example.campapp.services;

import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService{
    UtilisateurRepo userRepo;

    @Override
    public List<Utilisateur> getAllUser() {
        return userRepo.findAll();
    }
}
