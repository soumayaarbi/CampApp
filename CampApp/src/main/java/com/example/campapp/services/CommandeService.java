package com.example.campapp.services;

import com.example.campapp.entities.Commande;
import com.example.campapp.entities.Panier;
import com.example.campapp.entities.Produit;
import com.example.campapp.repositories.CommandeRepo;
import com.example.campapp.repositories.PanierRepo;
import com.example.campapp.repositories.ProduitRepo;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.itextpdf.layout.Document;

@Slf4j
@Service
@AllArgsConstructor

public class CommandeService implements ICommandeService{

   public  final CommandeRepo commandeRepo;

   public final PanierRepo panierRepo;
   public final ProduitRepo produitRepo;

    @Override
    public List<Commande> retrieveAllCommande() {
        return commandeRepo.findAll();
    }

    public Optional<Commande> retrieveCommandeById(Long idCommnde) {
        return commandeRepo.findById(idCommnde);
    }

    @Override

    public void deleteCommandeById(Long idCommande){
        commandeRepo.deleteById(idCommande);
    }

    @Override
    public Commande saveCommande(Commande c){
        return commandeRepo.save(c);
    }

    @Override
    public Commande updateCommande(Commande c){
        return commandeRepo.save(c);
    }

    @Override
    public Commande gestionRetoure(Commande c) {
        return null;
    }


//    public Commande convertirPanierEnCommande(Long panierId) {
//        Panier panier = panierRepo.findById(panierId)
//                .orElseThrow(() -> new IllegalArgumentException("Panier non trouvé"));
//
//        Commande commande = new Commande();
//        commande.setClient(panier.getClient());
//        commande.setAdresse(panier.getClient().getAdresse());
//        commande.setQuantite(panier.getProduits().size());
//        commande.setTarif((float) panier.getProduits().stream().mapToDouble(Produit::getPrix).sum());
//
//        Set<Produit> produitsCommande = new HashSet<>(panier.getProduits());
//        commande.setProduits(produitsCommande);
//
//        commandeRepo.save(commande);
//
//        panierRepo.delete(panier);
//
//        return commande;
//    }

    @Override
    @Transactional
    public Commande convertirPanierEnCommande(Long panierId) {
        Panier panier = panierRepo.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        // Création de la commande
        Commande commande = new Commande();
        commande.setClient(panier.getClient());
        commande.setAdresse(panier.getClient().getAdresse());
        commande.setQuantite(panier.getProduits().size()); // Vous pouvez ajuster cette logique en fonction des détails de chaque produit
        commande.setTarif((float) panier.getProduits().stream().mapToDouble(Produit::getPrix).sum());

        Set<Produit> produitsCommande = new HashSet<>();

        for (Produit produit : panier.getProduits()) {
            // Soustraire la quantité réservée
            if (produit.getQuantite() <= 0) {
                throw new RuntimeException("Produit " + produit.getNom() + " en rupture de stock");
            }
            produit.setQuantite(produit.getQuantite() - 1); // Décrémenter la quantité de 1 pour chaque produit dans le panier
            produitRepo.save(produit);

            produitsCommande.add(produit);
        }

        commande.setProduits(produitsCommande);
        return commandeRepo.save(commande);
    }

    @Override
    public Commande obtenirCommandeParId(Long idCommande) {
        Optional<Commande> commande = commandeRepo.findById(idCommande);
        return commande.orElse(null);
    }

    @Override
    public ByteArrayInputStream genererPdfCommande(Long idCommande) throws IOException {
        Commande commande = obtenirCommandeParId(idCommande);
        if (commande == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Titre du document
        document.add(new Paragraph("Commande #" + commande.getIdCommande()).setBold().setFontSize(20));
        document.add(new Paragraph("Client: " + commande.getClient().getNom()));
        document.add(new Paragraph("Adresse: " + commande.getAdresse()));
        document.add(new Paragraph(" "));

        // Table des produits
        Table table = new Table(new float[]{4, 1, 2});
        table.addCell(new Paragraph("Produit"));
        table.addCell(new Paragraph("Quantité"));
        table.addCell(new Paragraph("Prix"));

        commande.getProduits().forEach(produit -> {
            table.addCell(new Paragraph(produit.getNom()));
            table.addCell(new Paragraph(String.valueOf(commande.getQuantite())));
            table.addCell(new Paragraph(String.valueOf(produit.getPrix())));
        });

        document.add(table);
        document.add(new Paragraph("Total: " + commande.getTarif()));
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    }

