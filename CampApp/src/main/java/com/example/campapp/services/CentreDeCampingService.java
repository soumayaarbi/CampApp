package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.User;
import com.example.campapp.repositories.CentreDeCampingRepository;
import com.example.campapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentreDeCampingService {
    @Autowired
    private CentreDeCampingRepository centreDeCampingRepository;
@Autowired
private UserRepository utilisateurRepository;
    public CentreDeCamping findById(Long id) {
        return centreDeCampingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Centre de camping not found with id: " + id));
    }
    public CentreDeCamping createCentreDeCamping(CentreDeCamping centreDeCamping, Integer utilisateurId) {
        // Récupérer l'utilisateur connecté à partir de son ID
        User utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NotFoundException("Utilisateur not found with id: " + utilisateurId));
        // Affecter l'utilisateur au centre de camping
        centreDeCamping.setUtilisateur(utilisateur);
        return centreDeCampingRepository.save(centreDeCamping);
    }

    public List<CentreDeCamping> getAllCentresDeCamping() {
        return centreDeCampingRepository.findAll();
    }

    public CentreDeCamping getCentreDeCampingById(Long id) {
        return centreDeCampingRepository.findById(id).orElse(null);
    }
    public List<CentreDeCamping> getCentresDeCampingByUtilisateurId(Long utilisateurId) {
        return centreDeCampingRepository.findByUtilisateurId(utilisateurId);
    }
    public CentreDeCamping updateCentreDeCamping(Long id, CentreDeCamping updatedCentreDeCamping) {
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(id).orElse(null);
        if (centreDeCamping != null) {
            updatedCentreDeCamping.setIdCentre(id);
            return centreDeCampingRepository.save(updatedCentreDeCamping);
        }
        return null;
    }

    public boolean deleteCentreDeCamping(Long id) {
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(id).orElse(null);
        if (centreDeCamping != null) {
            centreDeCampingRepository.delete(centreDeCamping);
            return true;
        }
        return false;
    }
    public List<String> getAllLieux() {
        return centreDeCampingRepository.findAll()
                .stream()
                .map(CentreDeCamping::getLieu)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<CentreDeCamping> findCentresDeCampingByLieu(String lieu) {
        return centreDeCampingRepository.findByLieu(lieu);
    }
    public long countCentresByUtilisateurId(Long utilisateurId) {
        return centreDeCampingRepository.countByUtilisateurId(utilisateurId);
    }
}
