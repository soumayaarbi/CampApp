import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Forum } from './forum.model';

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  private baseUrl = 'http://localhost:8085/campApp/Forum';

  constructor(private http: HttpClient) { }

  getAllForum(): Observable<Forum[]> {
    return this.http.get<Forum[]>(`${this.baseUrl}/retrieve-all-forums`);
  }

  addForum(forum: Forum): Observable<Forum> {
    return this.http.post<Forum>(`${this.baseUrl}/add-forum`, forum);
  }

  deleteForum(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-forum/${id}`);
  }

  updateForum(forum: Forum): Observable<Forum> {
    return this.http.put<Forum>(`${this.baseUrl}/modify-forum`, forum);
  }
}
