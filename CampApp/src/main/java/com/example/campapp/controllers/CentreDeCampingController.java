package com.example.campapp.controllers;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.services.CentreDeCampingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://web.postman.com")
@RestController
@RequestMapping("/centresdecamping")
public class CentreDeCampingController {

    @Autowired
    private CentreDeCampingService centreDeCampingService;

    @PostMapping
    public ResponseEntity<CentreDeCamping> createCentreDeCamping(@RequestBody CentreDeCamping centreDeCamping ) {
        CentreDeCamping createdCentreDeCamping = centreDeCampingService.createCentreDeCamping(centreDeCamping);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCentreDeCamping);
    }

    @GetMapping
    public List<CentreDeCamping> getAllCentresDeCamping() {
        return centreDeCampingService.getAllCentresDeCamping();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreDeCamping> getCentreDeCampingById(@PathVariable Long id) {
        CentreDeCamping centreDeCamping = centreDeCampingService.getCentreDeCampingById(id);
        return centreDeCamping != null ?
                ResponseEntity.ok(centreDeCamping) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreDeCamping> updateCentreDeCamping(@PathVariable Long id, @RequestBody CentreDeCamping centreDeCamping) {
        CentreDeCamping updatedCentreDeCamping = centreDeCampingService.updateCentreDeCamping(id, centreDeCamping);
        return updatedCentreDeCamping != null ?
                ResponseEntity.ok(updatedCentreDeCamping) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCentreDeCamping(@PathVariable Long id) {
        boolean deleted = centreDeCampingService.deleteCentreDeCamping(id);
        return deleted ?
                ResponseEntity.ok("Centre de camping supprimé avec succès") :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/lieux")
    public ResponseEntity<List<String>> getAllLieux() {
        List<String> lieux = centreDeCampingService.getAllLieux();
        return new ResponseEntity<>(lieux, HttpStatus.OK);
    }

    @GetMapping("/centres/{lieu}")
    public ResponseEntity<List<CentreDeCamping>> getCentresByLieu(@PathVariable String lieu) {
        List<CentreDeCamping> centres = centreDeCampingService.findCentresDeCampingByLieu(lieu);
        return new ResponseEntity<>(centres, HttpStatus.OK);
    }
}
