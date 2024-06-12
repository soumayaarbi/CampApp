package com.example.campapp.controllers;

import com.example.campapp.entities.Panier;
import com.example.campapp.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @PostMapping("/creer/{utilisateurId}")
    public ResponseEntity<Panier> creerPanier(@PathVariable Long utilisateurId) {
        Panier panier = panierService.creerPanier(utilisateurId);
        return ResponseEntity.ok(panier);
    }

    @PostMapping("/{panierId}/ajouter/{produitId}")
    public ResponseEntity<Panier> ajouterProduit(@PathVariable Long panierId, @PathVariable Long produitId, @RequestParam int quantite) {
        Panier panier = panierService.ajouterProduitAuPanier(panierId, produitId, quantite);
        return ResponseEntity.ok(panier);
    }

    @PutMapping("/{panierId}/modifier/{produitId}")
    public ResponseEntity<Panier> modifierQuantite(@PathVariable Long panierId, @PathVariable Long produitId, @RequestParam int quantite) {
        Panier panier = panierService.modifierQuantiteProduit(panierId, produitId, quantite);
        return ResponseEntity.ok(panier);
    }

    @DeleteMapping("/{panierId}/supprimer/{produitId}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long panierId, @PathVariable Long produitId) {
        panierService.supprimerProduitDuPanier(panierId, produitId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Panier> obtenirPanierParUtilisateur(@PathVariable Long utilisateurId) {
        Panier panier = panierService.obtenirPanierParUtilisateur(utilisateurId);
        return ResponseEntity.ok(panier);
    }

    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> supprimerPanier(@PathVariable Long panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{panierId}/montant-total")
    public ResponseEntity<Float> calculerMontantTotal(@PathVariable Long panierId) {
        float montantTotal = panierService.calculerMontantTotal(panierId);
        return ResponseEntity.ok(montantTotal);
    }
}
