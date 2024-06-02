import { Component, OnInit } from '@angular/core';
import { Reclamation } from '../reclamation.model';
import { ReclamationService } from '../reclamation.service';

@Component({
  selector: 'app-reclamation-form',
  templateUrl: './reclamation-form.component.html',
  styleUrls: ['./reclamation-form.component.css']
})
export class ReclamationFormComponent implements OnInit {
  reclamation: Reclamation = new Reclamation(); // Créez une nouvelle instance de Reclamation

  constructor(private reclamationService: ReclamationService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.reclamationService.addReclamation(this.reclamation).subscribe(
      (response) => {
        console.log('Réclamation ajoutée avec succès !');
        // Réinitialiser le formulaire après l'ajout
        this.reclamation = new Reclamation();
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de la réclamation :', error);
        this.reclamation = new Reclamation();

      }
    );
  }
}
