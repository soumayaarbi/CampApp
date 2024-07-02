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

    @PostMapping("/{idCentre}")
    public Hebergement createHebergement(@PathVariable Long idCentre, @RequestBody Hebergement hebergement) {
        return hebergementService.createHebergement(idCentre, hebergement);
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
    public ResponseEntity<Void> deleteHebergement(@PathVariable Long id) {
        boolean isDeleted = hebergementService.deleteHebergement(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hebergement/{centreId}")
    public ResponseEntity<List<Hebergement>> getHebergementsByCentreId(@PathVariable Long centreId) {
        List<Hebergement> hebergements = hebergementService.findHebergementsByCentreId(centreId);
        return ResponseEntity.ok(hebergements);
    }
    @GetMapping("/hebergement")
    public ResponseEntity<List<Hebergement>> getAccommodationsByCentre(@RequestParam Long centreId) {
        if (centreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Hebergement> accommodations = hebergementService.findHebergementsByCentreId(centreId);
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }
    @GetMapping("/centre/{centreId}/capacite/{capacite}")
    public ResponseEntity<List<Hebergement>> getHebergementsByCentreAndCapacite(@PathVariable Long idCentre, @PathVariable int capacite) {
        List<Hebergement> hebergements = hebergementService.findHebergementsByCentreIdAndCapacite(idCentre, capacite);
        if (hebergements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hebergements, HttpStatus.OK);
    }

}
