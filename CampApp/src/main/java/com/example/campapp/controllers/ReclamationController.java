package com.example.campapp.controllers;
import com.example.campapp.entities.Boutique;
import com.example.campapp.entities.Reclamation;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.services.IReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reclamations")
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    private final IReclamationService reclamationService;

    @PostMapping
    public Reclamation addReclamation(@RequestBody Reclamation reclamation) {
        // Récupérer l'ID de l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Obtenez le nom d'utilisateur (ou identifiant) de l'utilisateur authentifié

        // Créer un objet Utilisateur avec l'ID de l'utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1L);

        // Associer l'utilisateur à la réclamation
        reclamation.setUtilisateur(utilisateur);

        // Ajouter la réclamation
        return reclamationService.addReclamation(reclamation);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReclamation(@PathVariable Long id) {
        reclamationService.removeReclamation(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public List<Reclamation> getReclamation() {

        return reclamationService.getAllReclamation();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/modify-reclamation")
    public Reclamation modifyReclamation(@RequestBody Reclamation reclamation) {
        Reclamation modifiedReclamation= reclamationService.modifyReclamation(reclamation);
        return modifiedReclamation;
    }

    @GetMapping("/count-by-sujet")
    public ResponseEntity<Map<String, Long>> countReclamationsBySujet() {
        Map<String, Long> countBySujet = reclamationService.countReclamationsBySujet();
        return ResponseEntity.ok(countBySujet);
    }

    @GetMapping("/filtre")
    public ResponseEntity<List<Reclamation>> filterReclamationsByDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByDay(date);
        return ResponseEntity.ok().body(reclamations);
    }
    /* exemple testing : http://localhost:8085/campApp/api/reclamations/filtre?date=2024-05-28*/


}
