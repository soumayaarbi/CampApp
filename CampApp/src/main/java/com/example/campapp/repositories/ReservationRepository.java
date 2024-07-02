package com.example.campapp.repositories;

import com.example.campapp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r")
    List<Reservation> findAllReservations();
    List<Reservation> findByUtilisateurId(Long userId);
    //Optional<Reservation> findByUtilisateurId(Long userId);
    List<Reservation> findTop5ByCentreDeCamping_IdCentreOrderByDateArriveeDesc(Long centreDeCampingId);
    long countByCentreDeCampingIdCentre(Long idCentre);
}
