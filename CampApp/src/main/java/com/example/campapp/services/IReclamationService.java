package com.example.campapp.services;

import com.example.campapp.entities.Reclamation;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IReclamationService {

    Reclamation addReclamation(Reclamation r);

    void removeReclamation(Long ReclamationId);



    List<Reclamation> getAllReclamation();

    Reclamation modifyReclamation(Reclamation reclamation);

    Map<String, Long> countReclamationsBySujet();

    List<Reclamation> getReclamationsByDay(Date date);

    Optional getUserByUsername(String username);
    Map<String, Long> countReclamationsByMonth();
    List<Reclamation> getReclamationsByYear(int year);
    Optional<Reclamation> getReclamationById(Long id);
}
