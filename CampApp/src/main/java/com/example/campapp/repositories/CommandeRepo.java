package com.example.campapp.repositories;

import com.example.campapp.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Long> {


    Commande save(Commande c);

    List<Commande> findAll();

    Optional<Commande> findById(Long idCommande);

    void deleteById(Long idCommande);
}