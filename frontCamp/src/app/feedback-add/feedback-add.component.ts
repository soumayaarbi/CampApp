// feedback-add.component.ts

import { Component } from '@angular/core';
import { Feedback } from '../feedbacks.model'; // Renommer le modèle en feedback.model
import { FeedbackService } from '../feedbacks.service';
import {CentredeCamp} from "../centredeCamp";
import {Utilisateur} from "../Utilisateur"; // Renommer le service en feedback.service

@Component({
  selector: 'app-feedback-add', // Renommer le sélecteur
  templateUrl: './feedback-add.component.html', // Renommer le fichier de modèle
  styleUrls: ['./feedback-add.component.css'] // Renommer le fichier de style
})
export class FeedbackAddComponent {
  feedback: Feedback = {centre: new CentredeCamp(), commentaire: '', date: new Date(), idFeedback: -1, note: 0, utilisateur: new Utilisateur()}; // Renommer la variable

  constructor(private feedbackService: FeedbackService) {} // Renommer le service

  onSubmit(): void {
    this.feedbackService.addFeedback(this.feedback)
      .subscribe(() => {
        console.log('Feedback added successfully');
        // Optionnellement, naviguer vers une autre page ou effectuer d'autres actions après la soumission réussie
      }, error => {
        console.error('Error adding feedback:', error);
        // Gérer l'erreur
      });
  }
}
