package com.example.campapp.services;

import com.example.campapp.dto.ReservationRequest;
import com.example.campapp.entities.*;
import com.example.campapp.repositories.*;
import jakarta.persistence.EntityNotFoundException;
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
    private UserRepository utilisateurRepository;

    public Reservation createReservation(Date dateArrivee, Date dateSortie, int nbrPersonne, String lieu, Long idHebergement, Long idEquipement, Long idCentre, Integer idUser) {
        Reservation reservation = new Reservation();
        reservation.setDateArivee(dateArrivee);
        reservation.setDateSortie(dateSortie);
        reservation.setNbrPersonne(nbrPersonne);
        reservation.setLieux(lieu);

        // Récupérer la liste des centres de camping en fonction du lieu choisi
        List<CentreDeCamping> centresDeCamping = centreDeCampingRepository.findByLieu(lieu);
        // Vous pouvez renvoyer cette liste de centres de camping à l'interface utilisateur pour qu'elle fasse un choix

        // Une fois que l'utilisateur a choisi un centre de camping, vous pouvez récupérer cet objet CentreDeCamping
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(idCentre).orElse(null);
        if (centreDeCamping == null) {
            // Gérer le cas où le centre de camping choisi n'existe pas
            return null;
        }

        // Récupérer l'hébergement spécifié par l'utilisateur
        Hebergement hebergement = hebergementRepository.findById(idHebergement).orElse(null);
        if (hebergement == null) {
            // Gérer le cas où l'hébergement spécifié n'existe pas
            return null;
        }

        // Vérifier si l'hébergement appartient bien au centre de camping choisi
        if (!hebergement.getCentreDeCamping().equals(centreDeCamping)) {
            // Gérer le cas où l'hébergement ne correspond pas au centre de camping choisi
            return null;
        }

        // Récupérer les équipements spécifiés par l'utilisateur
        Equipements equipement = equipementsRepository.findById(idEquipement).orElse(null);
        if (equipement == null) {
            // Gérer le cas où l'équipement spécifié n'existe pas
            return null;
        }

        // Vérifier si l'équipement appartient bien au centre de camping choisi
        if (!equipement.getCentreDeCamping().equals(centreDeCamping)) {
            // Gérer le cas où l'équipement ne correspond pas au centre de camping choisi
            return null;
        }

        // Récupérer l'utilisateur
        User user = utilisateurRepository.findById(idUser).orElse(null);
        if (user == null) {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }
        reservation.setUtilisateur(user);

        // Définir le centre de camping, l'hébergement et les équipements dans la réservation
        reservation.setCentreDeCamping(centreDeCamping);
        reservation.setHebergement(hebergement);
        reservation.setEquipement(equipement);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUtilisateurId(userId);
    }
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation updateReservation(Long id, Date dateArrivee, Date dateSortie, int nbrPersonne, String lieux, Long idHebergement, Long idEquipement, Long idCentre, Integer idUtilisateur) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            // Gérer le cas où la réservation n'existe pas
            return null;
        }

        reservation.setDateArivee(dateArrivee);
        reservation.setDateSortie(dateSortie);
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

        User utilisateur = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if (utilisateur == null) {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }
        reservation.setUtilisateur(utilisateur);

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            // Gérer le cas où la réservation n'existe pas
            return;
        }

        Date currentDate = new Date();
        Date arrivalDate = reservation.getDateArrivee();
        long diffInMillies = arrivalDate.getTime() - currentDate.getTime();
        long diffInHours = diffInMillies / (60 * 60 * 1000);

        if (diffInHours <= 24) {
            // Gérer le cas où l'annulation n'est pas autorisée
            return;
        }

        reservationRepository.delete(reservation);
    }
    public void updateReservationStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

}
