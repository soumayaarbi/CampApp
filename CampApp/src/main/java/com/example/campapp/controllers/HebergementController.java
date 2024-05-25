package com.example.campapp.controllers;

import com.example.campapp.entities.Hebergement;
import com.example.campapp.services.HebergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://web.postman.com")
@RestController
@RequestMapping("/hebergements")
public class HebergementController {

    @Autowired
    private HebergementService hebergementService;

    @PostMapping
    public ResponseEntity<Hebergement> createHebergement(@RequestBody Hebergement hebergement) {
        Hebergement createdHebergement = hebergementService.createHebergement(hebergement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHebergement);
    }

    @GetMapping
    public List<Hebergement> getAllHebergements() {
        return hebergementService.getAllHebergements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hebergement> getHebergementById(@PathVariable Long id) {
        Hebergement hebergement = hebergementService.getHebergementById(id);
        return hebergement != null ?
                ResponseEntity.ok(hebergement) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hebergement> updateHebergement(@PathVariable Long id, @RequestBody Hebergement hebergement) {
        Hebergement updatedHebergement = hebergementService.updateHebergement(id, hebergement);
        return updatedHebergement != null ?
                ResponseEntity.ok(updatedHebergement) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHebergement(@PathVariable Long id) {
        boolean deleted = hebergementService.deleteHebergement(id);
        return deleted ?
                ResponseEntity.ok("Hebergement supprimé avec succès") :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/hebergement")
    public ResponseEntity<List<Hebergement>> getAccommodationsByCentre(@RequestParam Long centreId) {
        if (centreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Hebergement> accommodations = hebergementService.findHebergementsByCentreId(centreId);
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }
}
