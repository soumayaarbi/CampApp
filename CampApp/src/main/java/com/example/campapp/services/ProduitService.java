package com.example.campapp.services;

import com.example.campapp.entities.Produit;
import com.example.campapp.repositories.ProduitRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProduitService implements IProduitService{

    @Override
    public Optional<Produit> findProductByNom(String nom) {
        return Optional.empty();
    }



    public  final ProduitRepo produitRepo;

    @Override
    public List<Produit> retrieveAllProduit() {
        return produitRepo.findAll();
    }

    public Optional<Produit> retrieveProduitById(Long idProduit) {
        return produitRepo.findById(idProduit);
    }

    @Override

    public void deleteProduitById(Long idCommande){
        produitRepo.deleteById(idCommande);
    }

    @Override
    public Produit saveProduit(Produit p){
        return produitRepo.save(p);
    }

    public Produit updateProduit(Long idProduit, Produit produit) {
        Optional<Produit> existingProduit = produitRepo.findById(idProduit);
        if (existingProduit.isPresent()) {
            Produit p = existingProduit.get();
            p.setNom(produit.getNom());
            p.setPrix(produit.getPrix());
            p.setQuantite(produit.getQuantite());
            p.setPromotion(produit.getPromotion());
            p.setDescription(produit.getDescription());

            return produitRepo.save(p);
        }
        return null;
    }




    // Method for managing stock of a product
    public Produit gestionStock(Produit p, int quantiteCommandee) {
        if (quantiteCommandee <= 0) {
            throw new IllegalArgumentException("La quantité commandée doit être positive.");
        }
        if (p.getQuantite() < quantiteCommandee) {
            throw new IllegalArgumentException("Stock insuffisant pour la commande.");
        }
        if (p.getQuantite() - quantiteCommandee < 10) {
            envoyerAlerteStockFaible(p);
        }
        int nouvelleQuantite = p.getQuantite() - quantiteCommandee;
        p.setQuantite(nouvelleQuantite);
        enregistrerTransaction(p, quantiteCommandee);
        return p;
    }
    private void envoyerAlerteStockFaible(Produit p) {
        System.out.println("Alerte : Stock faible pour le produit " + p.getNom() + " (Quantité actuelle : " + p.getQuantite() + ")");
    }


    private void enregistrerTransaction(Produit p, int quantiteCommandee) {
        System.out.println("Transaction enregistrée : Commande de " + quantiteCommandee + " unités du produit " + p.getNom());
    }

}

