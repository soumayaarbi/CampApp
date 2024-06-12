package com.example.campapp.services;

import com.example.campapp.entities.Panier;
import com.example.campapp.entities.Produit;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.PanierRepo;
import com.example.campapp.repositories.ProduitRepo;
import com.example.campapp.repositories.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class PanierService implements IPanierService{
    @Autowired
    private PanierRepo panierRepository;

    @Autowired
    private ProduitRepo produitRepository;

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    @Override
    public Panier creerPanier(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Panier panier = new Panier();
        panier.setClient(utilisateur);
        return panierRepository.save(panier);
    }

    @Override
    public Panier ajouterProduitAuPanier(Long panierId, Long produitId, int quantite) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        produit.setQuantite(quantite);
        panier.getProduits().add(produit);
        return panierRepository.save(panier);
    }

    @Override
    public Panier modifierQuantiteProduit(Long panierId, Long produitId, int quantite) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = panier.getProduits().stream()
                .filter(p -> p.getIdProduit().equals(produitId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit non trouvé dans le panier"));

        produit.setQuantite(quantite);
        return panierRepository.save(panier);
    }

    @Override
    public void supprimerProduitDuPanier(Long panierId, Long produitId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = panier.getProduits().stream()
                .filter(p -> p.getIdProduit().equals(produitId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit non trouvé dans le panier"));

        panier.getProduits().remove(produit);
        panierRepository.save(panier);
    }

    @Override
    public Panier obtenirPanierParUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateur.getPanier();
    }

    @Override
    public void supprimerPanier(Long panierId) {
        panierRepository.deleteById(panierId);
    }
    private static final Logger logger = Logger.getLogger(PanierService.class.getName());

    public Panier obtenirPanierParId(Long panierId) {
        logger.info("Recherche du panier avec l'ID: " + panierId);
        return panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + panierId));
    }

    public float calculerMontantTotal(Long panierId) {
        Panier panier = obtenirPanierParId(panierId);
        List<Produit> produits ;
        produits=panier.getProduits();
        float montantTotal = 0;
        for (int i=0;i<produits.size();i++)
        {
            Produit produit = produits.get(i);

                float prixProduit = produit.getPrix();
                montantTotal += prixProduit;
                System.out.println(montantTotal);

        }
        return montantTotal;
    }



}
