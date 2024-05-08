package com.example.campapp.repositories;

import com.example.campapp.entities.Hebergement;
import com.example.campapp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HebergementRepository extends JpaRepository<Hebergement, Long> {
}
