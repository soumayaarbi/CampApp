package com.example.campapp.controllers;

import com.example.campapp.entities.Reclamation;
import com.example.campapp.entities.Reponse;
import com.example.campapp.entities.User;
import com.example.campapp.services.IReponseService;
import com.example.campapp.services.IReclamationService;
import com.example.campapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {

    private final IReponseService reponseService;
    private final IReclamationService reclamationService;
    private final UserService userService;

    @Autowired
    public ReponseController(IReponseService reponseService, IReclamationService reclamationService, UserService userService) {
        this.reponseService = reponseService;
        this.reclamationService = reclamationService;
        this.userService = userService;
    }

    @PostMapping("/add-reponse")
    public ResponseEntity<?> addReponse(@RequestBody Reponse reponse, Authentication authentication) {
        try {
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.badRequest().body("Utilisateur non authentifié.");
            }

            // Ajout de journaux pour déboguer
            System.out.println("Reçu: " + reponse);
            System.out.println("Reclamation ID: " + reponse.getReclamation().getIdReclamation());
            System.out.println("User ID: " + reponse.getUser().getId());

            // Ajouter la réponse en utilisant votre service de réponse
            reponseService.addReponse(reponse);
            return ResponseEntity.ok("Réponse ajoutée avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout de la réponse.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReponse(@PathVariable Long id) {
        reponseService.removeReponse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-reclamation/{reclamationId}")
    public ResponseEntity<List<Reponse>> getReponsesByReclamation(@PathVariable Long reclamationId) {
        List<Reponse> reponses = reponseService.getReponsesByReclamation(reclamationId);
        return ResponseEntity.ok(reponses);
    }
}
