import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-main-responsable',
  templateUrl: './main-responsable.component.html',
  styleUrls: ['./main-responsable.component.css']
})
export class MainResponsableComponent {
  loggedInUser: any; // Déclarez loggedInUser de type any

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    this.loggedInUser = this.authService.getLoggedInUser(); // Récupérez l'utilisateur connecté
  }
}
