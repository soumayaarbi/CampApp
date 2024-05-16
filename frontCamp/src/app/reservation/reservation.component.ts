import { ReservationService } from './reservation.service';
import { Reservation } from '../Models/reservation';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'; // Importer le service MatDialog
import { AddReservationComponent } from '../add-reservation/add-reservation.component';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent implements OnInit {
  public reservations: Reservation[] = [];

  constructor(
    private reservationService: ReservationService,
    private dialog: MatDialog // Injecter le service MatDialog
  ) {}

  ngOnInit() {
    // Call the getReservations method from the service and assign the result to the reservations variable
    this.reservationService.getReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      },
      (error) => {
        console.error('Error fetching reservations:', error);
      }
    );
  }

  addReservation() {
    // Ouvrir le dialogue d'ajout de réservation
    const dialogRef = this.dialog.open(AddReservationComponent, {
      width: '400px', // Définir la largeur du dialogue
      disableClose: true, // Empêcher la fermeture du dialogue en cliquant en dehors de celui-ci
    });

    // Gérer les résultats du dialogue
    dialogRef.afterClosed().subscribe((result) => {
      // Actualiser les réservations si nécessaire
      // this.loadReservations();
    });
  }
}
