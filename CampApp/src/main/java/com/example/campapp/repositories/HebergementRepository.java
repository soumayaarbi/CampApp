package com.example.campapp.repositories;

import com.example.campapp.entities.Hebergement;
import com.example.campapp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HebergementRepository extends JpaRepository<Hebergement, Long> {
    List<Hebergement> findByCentreDeCampingIdCentre(Long idCentre);


    List<Hebergement> findByCentreDeCampingIdCentreAndCapaciteGreaterThanEqual(Long idCentre, int capacite);

}
