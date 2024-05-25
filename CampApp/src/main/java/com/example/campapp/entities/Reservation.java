package com.example.campapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private Date dateArrivee;
    private Date dateSortie;
    private int nbrPersonne;
    private String lieux;
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hebergement_id")
    private Hebergement hebergement;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private Equipements equipement;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "centre_id")
    private CentreDeCamping centreDeCamping;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User utilisateur;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    public Equipements getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipements equipement) {
        this.equipement = equipement;
    }

    public CentreDeCamping getCentreDeCamping() {
        return centreDeCamping;
    }

    public void setCentreDeCamping(CentreDeCamping centreDeCamping) {
        this.centreDeCamping = centreDeCamping;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
