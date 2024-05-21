import { ReservationService } from './reservation.service';
import { Reservation } from '../Models/reservation';
import { Component, OnInit } from '@angular/core';

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

  
}
