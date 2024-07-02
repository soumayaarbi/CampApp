package com.example.campapp.controllers;

import com.example.campapp.entities.Activite;
import com.example.campapp.entities.User;
import com.example.campapp.services.IActiviteService;
import com.example.campapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activites")
public class ActiviteController {

    @Autowired
    private final IActiviteService activiteService;

    @Autowired
    private final UserService userService;

    public ActiviteController(IActiviteService activiteService, UserService userService) {
        this.activiteService = activiteService;
        this.userService = userService;
    }

    @PostMapping("/add-activite")
    public ResponseEntity<?> addActivite(@RequestBody Activite activite) {
        try {
            // Récupérer l'ID de l'utilisateur à partir du contexte de sécurité
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Integer userId = getUserIdFromAuthentication(authentication);

            if (userId == null) {
                return ResponseEntity.badRequest().body("Impossible de récupérer l'ID de l'utilisateur.");
            }

            // Récupérer l'utilisateur correspondant à l'ID
            Optional<User> userOptional = userService.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Utilisateur non trouvé.");
            }
            User user = userOptional.get();

            // Assurez-vous que l'objet User est correctement défini sur l'objet Activite
            activite.setUser(user);

            // Ajouter l'activité à la base de données
            activiteService.addActivite(activite);

            return ResponseEntity.ok("Activité ajoutée avec succès");
        } catch (Exception e) {
            // Log l'erreur
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout de l'activité.");
        }
    }

    private Integer getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal).getId(); // Cast principal to your User class and get the ID
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActivite(@PathVariable Long id) {
        activiteService.removeActivite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Activite> getActivites() {
        return activiteService.getAllActivites();
    }

    @PutMapping("/modify-activite")
    public ResponseEntity<?> modifyActivite(@RequestBody Activite activite) {
        try {
            // Modify the activity in the database
            Activite updatedActivite = activiteService.modifyActivite(activite);
            return ResponseEntity.ok(updatedActivite);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while modifying the activity.");
        }
    }
}
