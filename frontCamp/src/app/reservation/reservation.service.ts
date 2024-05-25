import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { Reservation } from '../reservation.model';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private apiUrl = 'http://localhost:8085/campApp/reservations';
  private cUrl = 'http://localhost:8085/campApp/centresdecamping';
  private eUrl = 'http://localhost:8085/campApp/equipements';
  private hUrl = 'http://localhost:8085/campApp/hebergements';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}`);
  }

  getReservationsByCurrentUser(): Observable<Reservation[]> {
    return this.authService.getUserId().pipe(
      switchMap((userId: number | null) => {
        if (userId) {
          return this.http.get<Reservation[]>(`${this.apiUrl}/user/${userId}`);
        } else {
          throw new Error('User ID not available');
        }
      })
    );
  }

  getReservationsByUserId(userId: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/user/${userId}`);
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

  cancelReservation(id: number): Observable<any> {
    const url = `${this.apiUrl}/cancel/${id}`;
    return this.http.delete(url, { responseType: 'text' });
  }

  getAllLieux(): Observable<string[]> {
    return this.http.get<string[]>(`${this.cUrl}/lieux`);
  }

  getCentresByLocation(location: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.cUrl}/centres/${location}`);
  }

  getEquipmentsByCentre(centreId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.eUrl}/equipements?centreId=${centreId}`
    );
  }

  getAccommodationsByCentre(centreId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.hUrl}/hebergement?centreId=${centreId}`
    );
  }
}
