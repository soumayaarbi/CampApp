package com.example.campapp.controllers;

import com.example.campapp.entities.Reclamation;
import com.example.campapp.entities.User;
import com.example.campapp.services.IReclamationService;
import com.example.campapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {
    @Autowired
    private final IReclamationService reclamationService;


    @Autowired
    private final UserService userService;

    public ReclamationController(IReclamationService reclamationService, UserService userService) {
        this.reclamationService = reclamationService;
        this.userService = userService;
    }

    @PostMapping("/add-reclamation")
    public ResponseEntity<?> addReclamation(@RequestBody Reclamation reclamation) {
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

            // Assurez-vous que l'objet User est correctement défini sur l'objet Reclamation
            reclamation.setUser(user);

            // Ajouter la réclamation à la base de données
            reclamationService.addReclamation(reclamation);

            return ResponseEntity.ok("Réclamation ajoutée avec succès");
        } catch (Exception e) {
            // Log l'erreur
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout de la réclamation.");
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
    public ResponseEntity<Void> removeReclamation(@PathVariable Long id) {
        reclamationService.removeReclamation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Reclamation> getReclamation() {
        return reclamationService.getAllReclamation();
    }

    @PutMapping("/modify-reclamation")
    public Reclamation modifyReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.modifyReclamation(reclamation);
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

    @GetMapping("/count-by-month")
    public ResponseEntity<Map<String, Long>> countReclamationsByMonth() {
        Map<String, Long> countByMonth = reclamationService.countReclamationsByMonth();
        return ResponseEntity.ok(countByMonth);
    }

    @GetMapping("/by-year/{year}")
    public ResponseEntity<List<Reclamation>> getReclamationsByYear(@PathVariable int year) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByYear(year);
        return ResponseEntity.ok(reclamations);
    }

}