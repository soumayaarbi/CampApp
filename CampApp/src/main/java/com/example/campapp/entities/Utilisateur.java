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
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur; // Clé primaire
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Date datedenais;
    private String adresse;
    @Enumerated(EnumType.STRING)
    Role role;

    // Relation avec Boutique
    @OneToMany(mappedBy = "utilisateur")
    private Set<Boutique> boutiques;

    // Relation avec Panier
    @OneToOne(mappedBy = "client")
    private Panier panier;

    // Relation avec Commande
    @OneToMany(mappedBy = "client")
    private Set<Commande> commandes;







}