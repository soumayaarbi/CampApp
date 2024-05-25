import { Component, OnInit } from '@angular/core';
import { ReservationService } from './reservation.service';
import { Reservation } from '../reservation.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent implements OnInit {
  public reservations: Reservation[] = [];
  public userId: number | null = null;

  constructor(
    private reservationService: ReservationService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.authService.getUserId().subscribe(
      (userId: number | null) => {
        if (userId !== null) {
          this.userId = userId;
          this.loadReservations();
        } else {
          console.error('User ID not available');
        }
      },
      (error) => {
        console.error('Error fetching user ID:', error);
      }
    );
  }

  loadReservations(): void {
    if (this.userId !== null) {
      this.reservationService.getReservationsByUserId(this.userId).subscribe(
        (reservations: Reservation[]) => {
          this.reservations = reservations;
        },
        (error) => {
          console.error('Error fetching reservations:', error);
        }
      );
    }
  }

  cancelReservation(id: number): void {
    this.reservationService.cancelReservation(id).subscribe(
      (response) => {
        this.showNotification('success');
        this.loadReservations(); // Recharger la liste des réservations après l'annulation
      },
      (error) => {
        this.showNotification('error');
      }
    );
  }

  showNotification(type: 'success' | 'error'): void {
    const notification = document.getElementById(`${type}-notification`);
    if (notification) {
      notification.classList.add('show');
      setTimeout(() => {
        notification.classList.remove('show');
      }, 3000); // Masquer la notification après 3 secondes
    }
  }
}
