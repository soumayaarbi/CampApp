import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Produit} from "./produit.module";

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private apiUrl = 'http://localhost:8084/campingApp/Produit';
  constructor(private http: HttpClient) { }

  getAllProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/AllProduit`);
  }

  getProduitsByBoutiqueId(boutiqueId: number): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/produitByBoutique/${boutiqueId}`);
  }

  addProduit(produit: Produit): Observable<Produit> {
    return this.http.post<Produit>(`${this.apiUrl}/add-produit`, produit);
  }

  updateProduit(id:number, produit: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/update-produit/${id}`, produit);
  }

  deleteProduit(produitId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/remove-produit/${produitId}`);
  }
  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  }
}
