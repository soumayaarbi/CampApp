// utilisateur.component.ts

import { Component, OnInit } from '@angular/core';
import { UtilisateurService } from "../utilisateur.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements OnInit {

  utilisateurs: any;

  constructor(private utilisateurService: UtilisateurService, private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.utilisateurService.getAllUser()
      .subscribe(
        (res: any) => {
          console.log(res);
          this.utilisateurs = res;
        },
        (error) => {
          console.error('Erreur lors de la récupération des utilisateurs :', error);
        }
      );
  }
}
