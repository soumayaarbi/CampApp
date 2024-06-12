package com.example.campapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit; // Clé primaire
    private String nom;
    private float prix;
    private int Quantite;
    private String description ;
    private float Promotion;

    // Relation avec Boutique
    @ManyToOne
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;


}
