package com.example.campapp.services;

import com.example.campapp.entities.Boutique;
import com.example.campapp.entities.Reclamation;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReclamationService {
    Reclamation addReclamation(Reclamation r);
    void removeReclamation(Long ReclamationId);
    Reclamation getReclamationById(Long id);
    public List<Reclamation> getAllReclamation();
    public Reclamation modifyReclamation(Reclamation reclamation);

    public Map<String, Long> countReclamationsBySujet();

    public List<Reclamation> getReclamationsByDay(Date date);
}

