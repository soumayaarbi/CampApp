package com.example.campapp.services;

import com.example.campapp.entities.Commande;
import com.example.campapp.entities.Produit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICommandeService {
    List<Commande> retrieveAllCommande();

    Optional<Commande> retrieveCommandeById(Long idCommnde);

    void deleteCommandeById(Long idCommande);

    Commande saveCommande(Commande c);

    Commande updateCommande(Commande c);


    Commande gestionRetoure(Commande c);



    Commande convertirPanierEnCommande(Long panierId);

    Commande obtenirCommandeParId(Long idCommande);

    ByteArrayInputStream genererPdfCommande(Long idCommande) throws IOException;

}
