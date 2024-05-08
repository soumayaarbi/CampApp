package com.example.campapp.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@ToString
@EqualsAndHashCode
@Entity
public class CentreDeCamping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCentre; // Clé primaire
    private String nom;
    private String lieu;

    @OneToMany(mappedBy = "centreDeCamping")
    private List<Hebergement> hebergements;

    @OneToMany(mappedBy = "centreDeCamping")
    private List<Equipements> equipements;


    public CentreDeCamping() {

    }

    public Long getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(Long idCentre) {
        this.idCentre = idCentre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<Hebergement> getHebergements() {
        return hebergements;
    }

    public void setHebergements(List<Hebergement> hebergements) {
        this.hebergements = hebergements;
    }

    public List<Equipements> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipements> equipements) {
        this.equipements = equipements;
    }


}
