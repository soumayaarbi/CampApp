package com.example.campapp.repositories;
import com.example.campapp.dto.ProductStatisticsDTO;
import com.example.campapp.entities.Commande;
import com.example.campapp.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProduitRepo extends JpaRepository<Produit, Long> {


    Produit save(Produit p);

    List<Produit> findAll();

    Optional<Produit> findById(Long idProduit);

    void deleteById(Long idProduit);


}
