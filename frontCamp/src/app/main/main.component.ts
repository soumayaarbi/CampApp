import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  loggedInUser: any; // Déclarez loggedInUser de type any

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    this.loggedInUser = this.authService.getLoggedInUser(); // Récupérez l'utilisateur connecté
  }
}
