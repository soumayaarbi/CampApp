package com.example.campapp.services;

import com.example.campapp.entities.*;
import com.example.campapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HebergementRepository hebergementRepository;

    @Autowired
    private EquipementsRepository equipementsRepository;

    @Autowired
    private CentreDeCampingRepository centreDeCampingRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Reservation createReservation(Date dateArrivee, Date dateSortie, int nbrPersonne, String lieux,
                                         Long idHebergement, Long idEquipement, Long idCentre, Long idUtilisateur) {
        Reservation reservation = new Reservation();
        reservation.setDate_arivee(dateArrivee);
        reservation.setDate_sortie(dateSortie);
        reservation.setNbrPersonne(nbrPersonne);
        reservation.setLieux(lieux);

        Hebergement hebergement = hebergementRepository.findById(idHebergement).orElse(null);
        if (hebergement == null) {
            // Gérer le cas où l'hébergement n'existe pas
        }
        reservation.setHebergement(hebergement);

        Equipements equipement = equipementsRepository.findById(idEquipement).orElse(null);
        if (equipement == null) {
            // Gérer le cas où l'équipement n'existe pas
        }
        reservation.setEquipement(equipement);

        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(idCentre).orElse(null);
        if (centreDeCamping == null) {
            // Gérer le cas où le centre de camping n'existe pas
        }
        reservation.setCentreDeCamping(centreDeCamping);

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if (utilisateur == null) {
            // Gérer le cas où l'utilisateur n'existe pas
        }
        reservation.setUtilisateur(utilisateur);

        return reservationRepository.save(reservation);
    }




    public List<Reservation> getAllReservations() {
        return reservationRepository.findAllReservations();
    }


    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation updateReservation(Long id, Date dateArrivee, Date dateSortie, int nbrPersonne, String lieux, Long idHebergement, Long idEquipement, Long idCentre, Long idUtilisateur) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            // Gérer le cas où la réservation n'existe pas
            return null;
        }

        reservation.setDate_arivee(dateArrivee);
        reservation.setDate_sortie(dateSortie);
        reservation.setNbrPersonne(nbrPersonne);
        reservation.setLieux(lieux);

        Hebergement hebergement = hebergementRepository.findById(idHebergement).orElse(null);
        if (hebergement == null) {
            // Gérer le cas où l'hébergement n'existe pas
            return null;
        }
        reservation.setHebergement(hebergement);

        Equipements equipement = equipementsRepository.findById(idEquipement).orElse(null);
        if (equipement == null) {
            // Gérer le cas où l'équipement n'existe pas
            return null;
        }
        reservation.setEquipement(equipement);

        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(idCentre).orElse(null);
        if (centreDeCamping == null) {
            // Gérer le cas où le centre de camping n'existe pas
            return null;
        }
        reservation.setCentreDeCamping(centreDeCamping);

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if (utilisateur == null) {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }
        reservation.setUtilisateur(utilisateur);

        return reservationRepository.save(reservation);
    }
    // Vous pouvez ajouter d'autres méthodes CRUD si nécessaire

}
