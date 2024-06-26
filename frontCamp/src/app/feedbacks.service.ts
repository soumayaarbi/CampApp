import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import { Feedback } from './feedbacks.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = 'http://localhost:8085/campApp/Feedbacks';

  constructor(private http: HttpClient) { }

  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.baseUrl}/retrieve-all-feedbacks`);
  }

  addFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.baseUrl}/add-feedbacks`, feedback);
  }

  deleteFeedback(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-feedbacks/${id}`);
  }

  updateFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.put<Feedback>(`${this.baseUrl}/modify-feedbacks`, feedback);
  }


  getPublication( idPub: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/getPublication/${idPub}`).pipe(
      map(response => response)
    )
  }


    getReponsesParPublication(idCentre : number): Observable<Feedback[]> {

    const url = `${this.baseUrl}/getReponsesParPublication/${idCentre}`;

    return this.http.get<Feedback[]>(url).pipe(
      map(response=>response));
  }

}

