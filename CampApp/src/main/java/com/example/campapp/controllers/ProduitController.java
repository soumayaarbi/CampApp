package com.example.campapp.controllers;

import com.example.campapp.entities.Produit;
import com.example.campapp.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/Produit")
public class ProduitController {
    @Autowired
    private final ProduitService produitService;



    @PostMapping("/add-produit")
    public Produit addProduit (@RequestBody Produit p){
        return produitService.saveProduit(p);

    }

    @GetMapping("/AllProduit")
    public List<Produit> getAllProduits()
    {
        List<Produit> lists = produitService.retrieveAllProduit();
        return  lists;
    }
    @GetMapping("/getProduit/{produit-id}")
    public Optional<Produit> getProduits(@PathVariable("produit-id") Long produitId)
    {
        return produitService.retrieveProduitById(produitId);

    }

    @DeleteMapping("/remove-produit/{produit-id}")
    public void removeProduit(@PathVariable("produit-id") Long produitId) {
        produitService.deleteProduitById(produitId);
    }

    @PutMapping("/update-produit/{produit-id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        if (updatedProduit != null) {
            return ResponseEntity.ok(updatedProduit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}