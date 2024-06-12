package com.example.campapp.controllers;

import com.example.campapp.dto.ReservationRequest;
import com.example.campapp.entities.Reservation;
import com.example.campapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add-reservation")
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request.getDateArrivee(), request.getDateSortie(),
                request.getNbrPersonne(), request.getLieux(), request.getIdHebergement(), request.getIdEquipement(),
                request.getIdCentre(), request.getId());
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
        return reservationService.updateReservation(id, request.getDateArrivee(), request.getDateSortie(),
                request.getNbrPersonne(), request.getLieux(), request.getIdHebergement(), request.getIdEquipement(),
                request.getIdCentre(), request.getId());
    }
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

}






