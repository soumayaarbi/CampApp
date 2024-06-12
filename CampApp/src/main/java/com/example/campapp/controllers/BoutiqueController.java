package com.example.campapp.controllers;

import com.example.campapp.entities.Boutique;
import com.example.campapp.services.BoutiqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//swagerr
//http://localhost:8084/campingApp/swagger-ui/index.html#/boutique-controller


@RestController
@RequestMapping("/boutique")
@CrossOrigin(origins = "http://localhost:4200")
public class BoutiqueController {

    @Autowired
    private BoutiqueService boutiqueService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public List<Boutique> getAllBoutiques() {
        return boutiqueService.getAllBoutiques();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/byName/{nom}")
    public Boutique getBoutiqueByNom(@PathVariable String nom) {
        return boutiqueService.getBoutiqueByNomBoutique(nom);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    // Responsable : Créer une boutique
    @Secured("RESPENSABLECENTRE")
    @PostMapping
    public Boutique createBoutique(@RequestBody Boutique boutique) {
        return boutiqueService.createBoutique(boutique);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    // Admin : Mettre à jour une boutique
    @Secured("ADMINISTRATEUR")
    @PutMapping("/{id}")
    public ResponseEntity<Boutique> updateBoutique(@PathVariable Long id, @RequestBody Boutique boutique) {
        Boutique updatedBoutique = boutiqueService.updateBoutique(id, boutique);
        if (updatedBoutique != null) {
            return ResponseEntity.ok(updatedBoutique);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    // Admin : Supprimer une boutique
    @Secured("ADMINISTRATEUR")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoutique(@PathVariable Long id) {
        boutiqueService.deleteBoutique(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    // Admin et Responsable : Voir une boutique par ID
    @Secured({"ADMINISTRATEUR", "RESPENSABLECENTRE"})
    @GetMapping("/{id}")
    public ResponseEntity<Boutique> getBoutiqueById(@PathVariable Long id) {
        Boutique boutique = boutiqueService.getBoutiqueById(id);
        if (boutique != null) {
            return ResponseEntity.ok(boutique);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


