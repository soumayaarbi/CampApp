package com.example.campapp.dto;

import com.example.campapp.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class SignupRequest {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Date datedenais;
    @Enumerated(EnumType.STRING)
    Role role;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDatedenais() {
        return datedenais;
    }

    public void setDatedenais(Date datedenais) {
        this.datedenais = datedenais;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
