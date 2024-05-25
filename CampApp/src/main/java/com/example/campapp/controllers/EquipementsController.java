package com.example.campapp.controllers;

import com.example.campapp.entities.Equipements;
import com.example.campapp.services.EquipementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipements")
public class EquipementsController {

    private final EquipementsService equipementsService;

    @Autowired
    public EquipementsController(EquipementsService equipementsService) {
        this.equipementsService = equipementsService;
    }

    @GetMapping
    public List<Equipements> getAllEquipements() {
        return equipementsService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public Equipements getEquipementsById(@PathVariable Long id) {
        return equipementsService.getEquipementsById(id);
    }

    @PostMapping
    public Equipements saveEquipements(@RequestBody Equipements equipements) {
        return equipementsService.saveEquipements(equipements);
    }

    @PutMapping("/{id}")
    public Equipements updateEquipements(@PathVariable Long id, @RequestBody Equipements updatedEquipements) {
        Equipements existingEquipements = equipementsService.getEquipementsById(id);
        if (existingEquipements != null) {
            existingEquipements.setNom(updatedEquipements.getNom());
            existingEquipements.setDescription(updatedEquipements.getDescription());
            existingEquipements.setPrix(updatedEquipements.getPrix());
            return equipementsService.saveEquipements(existingEquipements);
        }
        return null; // ou vous pouvez gérer autrement si l'ID n'existe pas
    }

    @DeleteMapping("/{id}")
    public void deleteEquipements(@PathVariable Long id) {
        equipementsService.deleteEquipements(id);
    }

    @GetMapping("/equipements")
    public ResponseEntity<List<Equipements>> getEquipmentsByCentre(@RequestParam Long centreId) {
        if (centreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Equipements> equipments = equipementsService.findEquipementsByCentreDeCampingId(centreId);
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }
}
