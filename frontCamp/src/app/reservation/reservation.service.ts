import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../reservation.model'; // Assurez-vous que le chemin d'accès vers le modèle de réservation est correct

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private apiUrl = 'http://localhost:8085/campApp/reservations';

  constructor(private http: HttpClient) {}

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(
      `${this.apiUrl}`
    );
  }

  getReservation(reservationId: number): Observable<Reservation> {
    return this.http.get<Reservation>(
      `${this.apiUrl}/retrieve-reservation/${reservationId}`
    );
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(
      `${this.apiUrl}/add-reservation`,
      reservation
    );
  }

  deleteReservation(reservationId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/remove-reservation/${reservationId}`
    );
  }

  updateReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(
      `${this.apiUrl}/modify-reservation`,
      reservation
    );
  }
}
