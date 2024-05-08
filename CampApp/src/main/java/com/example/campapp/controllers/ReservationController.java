package com.example.campapp.controllers;

import com.example.campapp.dto.ReservationRequest;
import com.example.campapp.entities.Reservation;
import com.example.campapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request.getDateArrivee(), request.getDateSortie(),
                request.getNbrPersonne(), request.getLieux(), request.getIdHebergement(), request.getIdEquipement(),
                request.getIdCentre(), request.getIdUtilisateur());
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
        return reservationService.updateReservation(id, request.getDateArrivee(), request.getDateSortie(),
                request.getNbrPersonne(), request.getLieux(), request.getIdHebergement(), request.getIdEquipement(),
                request.getIdCentre(), request.getIdUtilisateur());
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}