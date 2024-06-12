import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boutique } from './boutique.model';

@Injectable({
  providedIn: 'root'
})
export class BoutiqueService {


  constructor(private http: HttpClient) {}
  private apiUrl = 'http://localhost:8084/campingApp/boutique';
  getAllBoutiques(){
    return this.http.get(this.apiUrl + '/all')

  }

  createBoutique(boutique: Boutique): Observable<Boutique> {
    return this.http.post<Boutique>(`${this.apiUrl}`, boutique);
  }

  getBoutiqueByNom(nom: string): Observable<Boutique> {
    return this.http.get<Boutique>(`${this.apiUrl}/byName/${nom}`);
  }
  updateBoutique(id: number, boutique: Boutique): Observable<Boutique> {
    return this.http.put<Boutique>(`${this.apiUrl}/${id}`, boutique);
  }
  getBoutiqueById(id: number): Observable<Boutique> {
    return this.http.get<Boutique>(`${this.apiUrl}/${id}`);
  }
  deleteBoutique(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
