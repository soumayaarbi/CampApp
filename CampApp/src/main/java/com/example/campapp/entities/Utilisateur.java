package com.example.campapp.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    Role role;

}