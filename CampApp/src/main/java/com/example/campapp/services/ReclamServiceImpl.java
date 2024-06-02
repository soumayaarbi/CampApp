package com.example.campapp.services;

import com.example.campapp.entities.Boutique;
import com.example.campapp.entities.Reclamation;
import com.example.campapp.entities.Reservation;
import com.example.campapp.repositories.ReclamationRepository;
import com.example.campapp.services.IReclamationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReclamServiceImpl implements IReclamationService {
    private final ReclamationRepository reclamationRepository;

    @Override
    public Reclamation addReclamation(Reclamation r) {
        Reclamation rec = reclamationRepository.save(r);
        return rec;
    }

    @Override
    public void removeReclamation(Long ReclamationId) {
        reclamationRepository.deleteById(ReclamationId);
    }



    @Override
    public Reclamation getReclamationById(Long id) {
        return null;
    }


    @Override
    public List<Reclamation> getAllReclamation() {
        return reclamationRepository.findAllReclamations();
    }
    @Override
    public Reclamation modifyReclamation(Reclamation reclamation) {
        Reclamation r=  reclamationRepository.save(reclamation);
        return r;
    }

    public Map<String, Long> countReclamationsBySujet() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations.stream()
                .collect(Collectors.groupingBy(Reclamation::getSujet, Collectors.counting()));
    }

    public List<Reclamation> getReclamationsByDay(Date date) {
        return reclamationRepository.findByDate(date);
    }

}



