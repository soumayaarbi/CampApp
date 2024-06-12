// update-boutique.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BoutiqueService } from '../boutique.service';
import { Boutique } from '../boutique.model';

@Component({
  selector: 'app-update-boutique',
  templateUrl: './update-boutique.component.html',
  styleUrls: ['./update-boutique.component.css']
})
export class UpdateBoutiqueComponent implements OnInit {
  updateBoutiqueForm: FormGroup;
  boutiqueId: number;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private boutiqueService: BoutiqueService
  ) {
    this.updateBoutiqueForm = this.formBuilder.group({
      nomBoutique: ['', Validators.required],
      owner: ['', Validators.required],
      description: ['', Validators.required]
    });
    this.boutiqueId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.loadBoutiqueDetails();
  }

  loadBoutiqueDetails(): void {
    this.boutiqueService.getBoutiqueById(this.boutiqueId).subscribe(
      (boutique) => {
        this.updateBoutiqueForm.patchValue(boutique);
      },
      (error) => {
        console.error('Erreur lors de la récupération des détails de la boutique', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateBoutiqueForm.invalid) {
      return;
    }

    const updatedBoutique: Boutique = this.updateBoutiqueForm.value;

    this.boutiqueService.updateBoutique(this.boutiqueId, updatedBoutique).subscribe(
      (response) => {
        console.log('Boutique mise à jour avec succès!', response);
        this.router.navigate(['/boutiques']);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de la boutique', error);
      }
    );
  }
}
