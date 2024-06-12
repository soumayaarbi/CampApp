package com.example.campapp.services;

import com.example.campapp.entities.Panier;

public interface IPanierService {
    Panier creerPanier(Long utilisateurId);
    Panier ajouterProduitAuPanier(Long panierId, Long produitId, int quantite);
    Panier modifierQuantiteProduit(Long panierId, Long produitId, int quantite);
    void supprimerProduitDuPanier(Long panierId, Long produitId);
    Panier obtenirPanierParUtilisateur(Long utilisateurId);
    void supprimerPanier(Long panierId);
    float calculerMontantTotal(Long panierId);
}
