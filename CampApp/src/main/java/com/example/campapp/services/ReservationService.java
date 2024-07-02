package com.example.campapp.services;

import com.example.campapp.dto.ReservationRequest;
import com.example.campapp.entities.*;
import com.example.campapp.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public Reservation createReservation(ReservationRequest reservationDTO) {

        Integer userId = reservationDTO.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur est manquant.");
        }

        CentreDeCamping centre = centreDeCampingRepository.findById(reservationDTO.getIdCentre())
                .orElseThrow(() -> new IllegalArgumentException("Centre introuvable"));

        Hebergement hebergement = hebergementRepository.findById(reservationDTO.getIdHebergement())
                .orElseThrow(() -> new IllegalArgumentException("Hébergement introuvable"));

        Equipements equipement = equipementsRepository.findById(reservationDTO.getIdEquipement())
                .orElseThrow(() -> new IllegalArgumentException("Équipement introuvable"));

        User utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé pour l'ID: " + userId));

        Reservation reservation = new Reservation();
        reservation.setDateArrivee(reservationDTO.getDateArrivee());
        reservation.setDateSortie(reservationDTO.getDateSortie());
        reservation.setNbrPersonne(reservationDTO.getNbrPersonne());
        reservation.setLieux(reservationDTO.getLieux());
        reservation.setCentreDeCamping(centre);
        reservation.setHebergement(hebergement);
        reservation.setEquipement(equipement);
        reservation.setStatus("pending");
        reservation.setUtilisateur(utilisateur);

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
    public void updateReservationDates(Long reservationId, Date dateArrivee, Date dateSortie) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));
        reservation.setDateArrivee(dateArrivee);
        reservation.setDateSortie(dateSortie);
        reservationRepository.save(reservation);
    }

    public void updateReservationStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }
    public Long getCentreWithMostReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();

        // Comptage des réservations par centre
        Map<Long, Long> reservationCountByCentre = allReservations.stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getCentreDeCamping().getIdCentre(), Collectors.counting()));

        // Trouver l'ID du centre avec le plus de réservations
        return reservationCountByCentre.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    public Map<String, Double> getReservationPercentagesByCentre() {
        List<Reservation> allReservations = reservationRepository.findAll();

        long totalReservations = allReservations.size();

        return allReservations.stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getCentreDeCamping().getNom(), // Utiliser le nom du centre comme clé
                        Collectors.counting()
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() * 100.0) / totalReservations
                ));
    }
    public List<Reservation> getRecentReservations(Long centreId) {
        return reservationRepository.findTop5ByCentreDeCamping_IdCentreOrderByDateArriveeDesc(centreId);
    }
    @Transactional
    public void acceptReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));

        if (!("pending".equals(reservation.getStatus()) || reservation.getStatus() == null)) {
            throw new IllegalStateException("Cannot accept reservation with status other than 'pending' or null.");
        }

        reservation.setStatus("accepted");
        reservationRepository.save(reservation);
    }

    @Transactional
    public void rejectReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));

        if (!("pending".equals(reservation.getStatus()) || reservation.getStatus() == null)) {
            throw new IllegalStateException("Cannot reject reservation with status other than 'pending' or null.");
        }

        reservation.setStatus("rejected");
        reservationRepository.save(reservation);
    }
    public long countReservationsByCentreId(Long idCentre) {
        return reservationRepository.countByCentreDeCampingIdCentre(idCentre);
    }

}
