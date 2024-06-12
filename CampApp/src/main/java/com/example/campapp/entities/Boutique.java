package com.example.campapp.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Boutique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoutique; // Clé primaire
    private String nomBoutique ;
    private Date dateCreation ;
    private String owner;
    private String Description;

    // Relation avec User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    // Relation avec Produit
    @OneToMany(mappedBy = "boutique")
    private Set<Produit> produits;
}