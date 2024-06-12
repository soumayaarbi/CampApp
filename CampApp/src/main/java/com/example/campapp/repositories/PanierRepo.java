package com.example.campapp.repositories;

import com.example.campapp.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepo extends JpaRepository<Panier, Long>{

    Panier findByClientIdUtilisateur(Long clientId);
}
