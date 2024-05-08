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
public class Hebergement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHebergement; // Clé primaire
    private String nom;
    private int capacite;
    private float prix ;
    private String  description;

    @ManyToOne
    private CentreDeCamping centreDeCamping;

    public Long getIdHebergement() {
        return idHebergement;
    }

    public void setIdHebergement(Long idHebergement) {
        this.idHebergement = idHebergement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CentreDeCamping getCentreDeCamping() {
        return centreDeCamping;
    }

    public void setCentreDeCamping(CentreDeCamping centreDeCamping) {
        this.centreDeCamping = centreDeCamping;
    }
}