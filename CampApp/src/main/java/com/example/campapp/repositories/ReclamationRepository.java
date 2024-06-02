package com.example.campapp.repositories;

import com.example.campapp.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    @Query("SELECT reser FROM Reclamation reser")
    List<Reclamation> findAllReclamations();

    List<Reclamation> findByDate(Date date);
}
