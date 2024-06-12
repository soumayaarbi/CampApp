package com.example.campapp.repositories;

import com.example.campapp.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoutiqueRepo extends JpaRepository<Boutique, Long> {
    Boutique findByNomBoutique(String nom);

}