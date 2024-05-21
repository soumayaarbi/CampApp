import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

interface UpdateResponse {
  success: boolean;
  message: string;
  updatedUser?: any; // Optionnel, si vous renvoyez des données utilisateur mises à jour dans la réponse
}
interface AuthenticationResponse {
  token: string;
  message: string;
  username: string;
  firstName: string;
  lastName: string;
  role: string; // Add role to the response interface
}

interface User {
  username: string;
  password: string;
  firstName?: string;
  lastName?: string;
  token?: string;
  role?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8085/campApp'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  signin(user: User): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/login`, user).pipe(
      tap(response => {
        if (response && response.token && response.username && response.firstName && response.lastName && response.role) {
          const loggedInUser: User = {
            username: response.username,
            password: user.password,
            firstName: response.firstName,
            lastName: response.lastName,
            role: response.role,
            token: response.token
          };
          this.setLoggedInUser(loggedInUser);
        }
      })
    );
  }

  public setLoggedInUser(user: User): void {
    localStorage.setItem('username', user.username);
    localStorage.setItem('firstName', user.firstName!);
    localStorage.setItem('lastName', user.lastName!);
    localStorage.setItem('role', user.role!);
    localStorage.setItem('password', user.password);
    localStorage.setItem('token', user.token!);
  }

  getLoggedInUser(): User | null {
    const username = localStorage.getItem('username');
    const firstName = localStorage.getItem('firstName');
    const lastName = localStorage.getItem('lastName');
    const role = localStorage.getItem('role');
    const password = localStorage.getItem('password');
    const token = localStorage.getItem('token');

    if (username && firstName && lastName && password && role && token) {
      return {
        username: username,
        firstName: firstName,
        lastName: lastName,
        password: password,
        role: role,
        token: token
      };
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem('username');
    localStorage.removeItem('firstName');
    localStorage.removeItem('lastName');
    localStorage.removeItem('role');
    localStorage.removeItem('password');
    localStorage.removeItem('token');
  }

  signup(user: User): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/register`, user);
  }

  isLoggedIn(): Observable<boolean> {
    const loggedInUser = this.getLoggedInUser();
    return of(!!loggedInUser); // Convert to boolean (true if user is logged in, false otherwise)
  }
  updateUserProfile(updatedUser: any): Observable<UpdateResponse> {
    // Assume that your backend API endpoint for updating user profile is '/updateProfile'
    return this.http.put<UpdateResponse>(`${this.apiUrl}/updateProfile`, updatedUser);
  }
}
