package com.example.campapp.controllers;

import com.example.campapp.entities.Forum;
import com.example.campapp.entities.User;
import com.example.campapp.services.IForumService;
import com.example.campapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    private final IForumService forumService;
    private final UserService userService;

    @Autowired
    public ForumController(IForumService forumService, UserService userService) {
        this.forumService = forumService;
        this.userService = userService;
    }

    @PostMapping("/add-forum")
    public ResponseEntity<?> addForum(@RequestBody Forum forum) {
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

            // Assurez-vous que l'objet User est correctement défini sur l'objet Forum
            forum.setUser(user);

            // Ajouter le forum à la base de données
            forumService.addForum(forum);

            return ResponseEntity.ok("Forum ajouté avec succès");
        } catch (Exception e) {
            // Log l'erreur
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du forum.");
        }
    }

    private Integer getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getId(); // Cast principal to your User class and get the ID
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeForum(@PathVariable Long id) {
        forumService.removeForum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }

    @PutMapping("/modify-forum")
    public ResponseEntity<Forum> modifyForum(@RequestBody Forum forum) {
        Forum updatedForum = forumService.modifyForum(forum);
        return ResponseEntity.ok(updatedForum);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long id) {
        Optional<Forum> forum = forumService.getForumById(id);
        return forum.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
