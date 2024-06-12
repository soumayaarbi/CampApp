// add-boutique.component.ts

/*import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Boutique } from '../boutique.model';
import { Utilisateur } from '../reservation.model'; // Assurez-vous que le modèle utilisateur est correctement importé
import { BoutiqueService } from '../boutique.service';
import {getLocaleDateFormat} from "@angular/common";

@Component({
  selector: 'app-add-boutique',
  templateUrl: './add-boutique.component.html',
  styleUrls: ['./add-boutique.component.css']
})
export class AddBoutiqueComponent implements OnInit {
  addBoutiqueForm: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private boutiqueService: BoutiqueService
  ) {
    this.addBoutiqueForm = this.formBuilder.group({
      nomBoutique: ['', Validators.required],
      owner: ['', Validators.required],
      dateCreation: [new Date(), Validators.required], // Initialize with current date
      utilisateur: [utilisateurService.getAllusers(1)],
      description: ['', Validators.required],


    });
  }

  ngOnInit(): void {

  }

  onSubmit(): void {
    if (this.addBoutiqueForm.invalid) {
      return;
    }

    const boutiqueData: Boutique = this.addBoutiqueForm.value;

    this.boutiqueService.createBoutique(boutiqueData).subscribe(
      (response) => {
        console.log('Boutique créée avec succès!', response);
        this.router.navigate(['/boutiques']);
      },
      (error) => {
        console.error('Erreur lors de la création de la boutique', error);
      }
    );
  }
}
*/
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Boutique} from "../boutique.model";
import {BoutiqueService} from '../boutique.service';
import {UtilisateurService} from '../utilisateur.service';
import {Role} from "../reservation.model";

@Component({
  selector: 'app-add-boutique',
  templateUrl: './add-boutique.component.html',
  styleUrls: ['./add-boutique.component.css']
})
export class AddBoutiqueComponent implements OnInit {
  addBoutiqueForm: FormGroup;
  utilisateurs: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private boutiqueService: BoutiqueService,
    private utilisateurService: UtilisateurService
  ) {
    this.addBoutiqueForm = this.formBuilder.group({
      nomBoutique: ['', Validators.required],
      owner: ['', Validators.required],
      dateCreation: [new Date(), Validators.required],
      utilisateurId: [1, Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  onSubmit(): void {
    if (this.addBoutiqueForm.invalid) {
      return;
    }

    const formValues = this.addBoutiqueForm.getRawValue();
    const utilisateur = this.utilisateurs.find(u => u.idUtilisateur === formValues.utilisateurId);

    const boutiqueData: Boutique = {
      produits: [],
      nomBoutique: formValues.nomBoutique,
      owner: formValues.owner,
      dateCreation: formValues.dateCreation,
      description: formValues.description,
      utilisateur : {
        idUtilisateur : 1
      }
    };

    this.boutiqueService.createBoutique(boutiqueData).subscribe(
      (response) => {
        console.log('Boutique créée avec succès!', response);
        this.router.navigate(['/boutiques']);
      },
      (error) => {
        console.error('Erreur lors de la création de la boutique', error);
      }
    );
  }
}


