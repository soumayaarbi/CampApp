import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {CentredeCamp} from "./centredeCamp";

@Injectable({
  providedIn: 'root'
})
export class CentreService {
  private baseUrl = 'http://localhost:8085/campApp/Centre';
  constructor(private _httpClient: HttpClient) { }


  getAllPublications(): Observable<CentredeCamp[]> {
    const url = `${this.baseUrl}/retrieve-all-centres`;
    return this._httpClient.get<CentredeCamp[]>(url).pipe(
        map(response=>response));
  }
}
