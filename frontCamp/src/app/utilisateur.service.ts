// utilisateur.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private apiUrl = 'http://localhost:8084/campingApp/user';

  constructor(private http: HttpClient) {}

  getAllUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/all`);
  }
}
