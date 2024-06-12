package com.example.campapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "centre_de_camping_id") // Nom de la colonne dans la table Equipements
    private CentreDeCamping centreDeCamping;

    public Long getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(Long idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public CentreDeCamping getCentreDeCamping() {
        return centreDeCamping;
    }

    public void setCentreDeCamping(CentreDeCamping centreDeCamping) {
        this.centreDeCamping = centreDeCamping;
    }
}