package com.example.campapp.controllers;

import com.example.campapp.dto.ReservationRequest;
import com.example.campapp.entities.Reservation;
import com.example.campapp.services.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservations")
public class ReservationController {
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add-reservation")
    public ResponseEntity<?> addReservation(@RequestBody ReservationRequest reservationRequest) {
        logger.debug("Requête de réservation reçue: {}", reservationRequest);

        if (reservationRequest.getUserId() == null) {
            logger.error("L'ID utilisateur est manquant dans le corps de la requête.");
            return ResponseEntity.badRequest().body("L'ID utilisateur est manquant.");
        }

        try {
            reservationService.createReservation(reservationRequest);
            return ResponseEntity.ok("Réservation ajoutée avec succès");
        } catch (IllegalArgumentException e) {
            logger.error("Erreur lors de l'ajout de la réservation:", e);
            return ResponseEntity.badRequest()
                    .body("Une erreur s'est produite lors de l'ajout de la réservation.");
        }
    }



    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

   // @PutMapping("/{id}")
   // public Reservation updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
       // return reservationService.updateReservation(id, request.getDateArrivee(), request.getDateSortie(),
          //      request.getNbrPersonne(), request.getLieux(), request.getIdHebergement(), request.getIdEquipement(),
          //      request.getIdCentre(), request.getId());
    //}
    @GetMapping("user/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.findReservationsByUserId(userId);
    }
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);

            if (reservation == null) {
                return ResponseEntity.notFound().build(); // La réservation n'existe pas
            }

            if ("annulée".equals(reservation.getStatus())) {
                return ResponseEntity.badRequest().body("La réservation est déjà annulée.");
            }

            LocalDate today = LocalDate.now();
            LocalDate dateArrivee = reservation.getDateArrivee().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long differenceInDays = ChronoUnit.DAYS.between(today, dateArrivee);

            if (differenceInDays < 1) {
                return ResponseEntity.badRequest().body("La réservation ne peut pas être annulée car elle est à moins de 24 heures de sa date d'arrivée.");
            }

            // Mettre à jour le statut de la réservation à "ANNULÉ"
            reservationService.updateReservationStatus(id, "annulée");

            return ResponseEntity.ok("La réservation a été annulée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de l'annulation de la réservation.");
        }
    }
    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable("id") Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Réservation non trouvée", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id,
                                               @RequestParam("dateArrivee") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateArrivee,
                                               @RequestParam("dateSortie") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateSortie) {
        reservationService.updateReservationDates(id, dateArrivee, dateSortie);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/most-reservations-centre")
    public ResponseEntity<Long> getCentreWithMostReservations() {
        Long centreId = reservationService.getCentreWithMostReservations();
        return ResponseEntity.ok(centreId);
    }
    @GetMapping("/reservation-percentages")
    public ResponseEntity<Map<String, Double>> getReservationPercentagesByCentre() {
        Map<String, Double> percentages = reservationService.getReservationPercentagesByCentre();
        return ResponseEntity.ok(percentages);
    }
    @GetMapping("/recent-reservations/{centreId}")
    public List<Reservation> getRecentReservations(@PathVariable Long centreId) {
        return reservationService.getRecentReservations(centreId);
    }
    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptReservation(@PathVariable("id") Long id) {
        reservationService.acceptReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectReservation(@PathVariable("id") Long id) {
        reservationService.rejectReservation(id);
        return ResponseEntity.ok().build();
    }
}






