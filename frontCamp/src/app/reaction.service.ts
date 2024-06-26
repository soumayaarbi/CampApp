import { Injectable } from '@angular/core';
import {Reaction} from "./Reaction";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReactionService {
  constructor(private _httpClient : HttpClient) { }
  private baseUrl = 'http://localhost:8085/campApp/Reaction';
  reaction !: Reaction;

  saveReaction(react: Reaction): Observable<Reaction>{
    const url = `${this.baseUrl}/addReaction`;
    return this._httpClient.post<Reaction>(url, react);
  }



  getReactionsLikeParPublication(idPub : number): Observable<Reaction[]> {

    const url = `${this.baseUrl}/getReactionsLikeParPublication/${idPub}`;

    return this._httpClient.get<Reaction[]>(url).pipe(
      map(response=>response));
  }


  getReactionsDisLikeParPublication(idPub : number): Observable<Reaction[]> {

    const url = `${this.baseUrl}/getReactionsDisLikeParPublication/${idPub}`;

    return this._httpClient.get<Reaction[]>(url).pipe(
      map(response=>response));
  }
}
