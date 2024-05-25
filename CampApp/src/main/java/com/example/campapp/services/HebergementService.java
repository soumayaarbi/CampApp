package com.example.campapp.services;

import com.example.campapp.entities.Hebergement;
import com.example.campapp.repositories.HebergementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HebergementService {

    @Autowired
    private HebergementRepository hebergementRepository;

    public Hebergement createHebergement(Hebergement hebergement) {
        return hebergementRepository.save(hebergement);
    }

    public List<Hebergement> getAllHebergements() {
        return hebergementRepository.findAll();
    }

    public Hebergement getHebergementById(Long id) {
        return hebergementRepository.findById(id).orElse(null);
    }

    public Hebergement updateHebergement(Long id, Hebergement updatedHebergement) {
        Hebergement hebergement = hebergementRepository.findById(id).orElse(null);
        if (hebergement != null) {
            updatedHebergement.setIdHebergement(id);
            return hebergementRepository.save(updatedHebergement);
        }
        return null;
    }

    public boolean deleteHebergement(Long id) {
        Hebergement hebergement = hebergementRepository.findById(id).orElse(null);
        if (hebergement != null) {
            hebergementRepository.delete(hebergement);
            return true;
        }
        return false;
    }
    public List<Hebergement> findHebergementsByCentreDeCamping(Long idCentre) {
        // Implémentez la logique pour récupérer les types d'hébergement en fonction du centre de camping
        return hebergementRepository.findByCentreDeCampingIdCentre(idCentre);
    }
    public List<Hebergement> findHebergementsByCentreId(Long centreId) {
        return hebergementRepository.findByCentreDeCampingIdCentre(centreId);
    }
}