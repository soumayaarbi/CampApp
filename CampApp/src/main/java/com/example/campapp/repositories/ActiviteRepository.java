package com.example.campapp.repositories;

import com.example.campapp.entities.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    @Query("SELECT a FROM Activite a")
    List<Activite> findAllActivites();
}
