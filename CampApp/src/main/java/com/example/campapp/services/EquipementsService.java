package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Equipements;
import com.example.campapp.repositories.CentreDeCampingRepository;
import com.example.campapp.repositories.EquipementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipementsService {

    private final EquipementsRepository equipementsRepository;
    @Autowired
    private CentreDeCampingRepository centreDeCampingRepository;
    @Autowired
    public EquipementsService(EquipementsRepository equipementsRepository) {
        this.equipementsRepository = equipementsRepository;
    }

    public List<Equipements> getAllEquipements() {
        return equipementsRepository.findAll();
    }

    public Equipements getEquipementsById(Long id) {
        return equipementsRepository.findById(id).orElse(null);
    }

    public Equipements saveEquipements(Equipements equipements) {
        return equipementsRepository.save(equipements);
    }

    public void deleteEquipements(Long id) {
        equipementsRepository.deleteById(id);
    }
    public List<Equipements> findEquipementsByCentreDeCamping(Long idCentre) {
        // Implémentez la logique pour récupérer les équipements en fonction du centre de camping
        return equipementsRepository.findByCentreDeCampingIdCentre(idCentre);
    }
    public List<Equipements> findEquipementsByCentreDeCampingId(Long centreId) {
        return equipementsRepository.findByCentreDeCampingIdCentre(centreId);
    }
    public Equipements createEquipement(Long idCentre, Equipements equipement) {
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(idCentre).orElse(null);
        if (centreDeCamping != null) {
            equipement.setCentreDeCamping(centreDeCamping);
            return equipementsRepository.save(equipement);
        }
        return null;
    }

}