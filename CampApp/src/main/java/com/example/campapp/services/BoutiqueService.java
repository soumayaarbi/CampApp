package com.example.campapp.services;

import com.example.campapp.entities.Boutique;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.BoutiqueRepo;
import com.example.campapp.repositories.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class BoutiqueService implements IBoutiqueService{
    private BoutiqueRepo boutiqueRepository;
private UtilisateurRepo utilisateurRepo;
    @Override
    public Boutique createBoutique(Boutique boutique) {

        Utilisateur user = utilisateurRepo.findById(boutique.getUtilisateur().getIdUtilisateur()).get();
        boutique.setUtilisateur(user);
        return boutiqueRepository.save(boutique);
    }

    @Override
    public Boutique updateBoutique(Long id, Boutique boutique) {
        Optional<Boutique> existingBoutique = boutiqueRepository.findById(id);
        if (existingBoutique.isPresent()) {
            Boutique b = existingBoutique.get();
            b.setNomBoutique(boutique.getNomBoutique());
            b.setDescription(boutique.getDescription());
            // Mettez à jour d'autres attributs si nécessaire
            return boutiqueRepository.save(b);
        }
        return null;
    }

    @Override
    public void deleteBoutique(Long id) {
        boutiqueRepository.deleteById(id);
    }

    @Override
    public Boutique getBoutiqueById(Long id) {
        return boutiqueRepository.findById(id).orElse(null);
    }

    @Override
    public List<Boutique> getAllBoutiques() {
        return boutiqueRepository.findAll();
    }

    public Boutique getBoutiqueByNomBoutique(String nom) {
        return boutiqueRepository.findByNomBoutique(nom);
    }

}

