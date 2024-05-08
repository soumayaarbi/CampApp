package com.example.campapp.repositories;

import com.example.campapp.entities.Equipements;
import com.example.campapp.entities.Hebergement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipementsRepository  extends JpaRepository<Equipements, Long> {
    Optional<Equipements> findById(Long idEquipement);
}
