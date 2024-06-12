package com.example.campapp.services;

import com.example.campapp.entities.Produit;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface IProduitService {

    List<Produit> retrieveAllProduit();
    Optional<Produit> retrieveProduitById(Long idCommnde);
    void deleteProduitById(Long idProduit);
    Produit saveProduit(Produit p);
    public Produit updateProduit(Long idProduit, Produit updatedProduit);



    Optional<Produit> findProductByNom(String nom);

}
