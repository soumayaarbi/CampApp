package com.example.campapp.repositories;


import com.example.campapp.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findAll();
}
