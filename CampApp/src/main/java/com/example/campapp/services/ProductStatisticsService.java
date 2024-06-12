package com.example.campapp.services;

import com.example.campapp.dto.ProductStatisticsDTO;
import com.example.campapp.entities.Panier;
import com.example.campapp.entities.Produit;
import com.example.campapp.repositories.PanierRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductStatisticsService implements IProductStatisticsService {

    private final PanierRepo panierRepo;

    @Autowired
    public ProductStatisticsService(PanierRepo panierRepo) {
        this.panierRepo = panierRepo;
    }


    @Override
    public List<ProductStatisticsDTO> getTopSellingProducts() {
        List<Panier> paniers = panierRepo.findAll();

        Map<String, Long> productSales = new HashMap<>();

        for (Panier panier : paniers) {
            List<Produit> produits = panier.getProduits();
            for (Produit produit : produits) {
                productSales.put(produit.getNom(), productSales.getOrDefault(produit.getNom(), 2L) + produit.getQuantite());
            }
        }

        return productSales.entrySet().stream()
                .map(entry -> new ProductStatisticsDTO(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> b.getTotalVendu().compareTo(a.getTotalVendu()))
                .collect(Collectors.toList());
    }
}
