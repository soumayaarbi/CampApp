package com.example.campapp.services;

import com.example.campapp.dto.SignupRequest;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.UtilisateurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired

    public AuthServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUtilisateur(SignupRequest signupRequest) {
        //check if user already exist
        if (utilisateurRepository.existsByEmail(signupRequest.getEmail())){
            return false;}
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(signupRequest,utilisateur);
       // utilisateur.setEmail(signupRequest.getEmail())
        // utilisateur.setNom(signupRequest.getNom());
        //utilisateur.setPrenom((signupRequest.getPrenom()));
       // utilisateur.setDatedenais(signupRequest.getDatedenais());
        //utilisateur.setRole(signupRequest.getRole());
        //utilisateur.setMotDePasse(signupRequest.getMotDePasse());
        //hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getMotDePasse());
        utilisateur.setMotDePasse(hashPassword);
        utilisateurRepository.save(utilisateur);
        return true;
    }
}
