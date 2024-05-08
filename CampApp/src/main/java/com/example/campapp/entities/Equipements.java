package com.example.campapp.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Equipements implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement; // Clé primaire
    private String nom;
    private String description;
    private float prix ;

    @ManyToOne
    private CentreDeCamping centreDeCamping;




}