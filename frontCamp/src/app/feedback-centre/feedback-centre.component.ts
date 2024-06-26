import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Feedback} from "../feedbacks.model";
import {CentredeCamp} from "../centredeCamp";
import {Utilisateur} from "../Utilisateur";
import {FeedbackService} from "../feedbacks.service";

@Component({
  selector: 'app-feedback-centre',
  templateUrl: './feedback-centre.component.html',
  styleUrls: ['./feedback-centre.component.css']
})
export class FeedbackCentreComponent implements OnInit {
    feedbacks: Feedback[] = [];
    selectedFeedback: Feedback | null = null;
  pubId : any;
  feedback: Feedback = { idFeedback: -1, commentaire: '', note: 0, date: new Date()  , centre : new CentredeCamp() , utilisateur : new Utilisateur()};
  constructor(private _activatedRoute: ActivatedRoute , private  feedbackService : FeedbackService) {
  }


  ngOnInit(): void {

    this.pubId = Number(this._activatedRoute.snapshot.paramMap.get('id'));
    this.getfeedbackParCentre(this.pubId) ;
  }



  saveReponse(){
    this.feedback.date = new Date();
    this.feedback.utilisateur = new Utilisateur();
    this.feedback.utilisateur.idUtilisateur = 1;
    this.feedback.centre = new CentredeCamp();
    this.feedback.centre.idCentre = this.pubId;
    this.feedbackService.addFeedback(this.feedback).subscribe(
        (res) => {
          console.log("succès");
          this.getfeedbackParCentre(this.pubId) ;

        },
        (err) => {
          console.log(err);
        }
    )
  }

getfeedbackParCentre(idCentre : number) {
    this.feedbackService.getReponsesParPublication(idCentre).subscribe(
        (res) => {
            this.feedbacks = res ;
          console.log("succès");
        },
        (err) => {
          console.log(err);
        }
    )
}

    deleteFeedback(feedbackId: number): void {
        if (confirm("Êtes-vous sûr de vouloir supprimer ce feedback ?")) {
            this.feedbackService.deleteFeedback(feedbackId)
                .subscribe(
                    () => {
                        this.feedbacks = this.feedbacks.filter(f => f.idFeedback !== feedbackId);
                    },
                    (error) => {
                        console.error('Une erreur s\'est produite lors de la suppression du feedback :', error);
                    }
                );
        }
    }

    editFeedback(feedback: Feedback): void {
        this.selectedFeedback = feedback;
    }

    fetchFeedbacks(): void {
        this.feedbackService.getAllFeedbacks()
            .subscribe(
                (feedbacks: Feedback[]) => {
                    this.feedbacks = feedbacks;
                },
                (error) => {
                    console.error('Une erreur s\'est produite lors de la récupération des feedbacks :', error);
                }
            );
    }
    updateFeedback(): void {
        if (this.selectedFeedback) {
            this.selectedFeedback.date = new Date() ;
            this.feedbackService.updateFeedback(this.selectedFeedback)
                .subscribe(
                    (updatedFeedback: Feedback) => {
                        console.log('Feedback updated successfully:', updatedFeedback);
                        // Réinitialiser le feedback sélectionné
                        this.selectedFeedback = null;
                        // Rechargez la liste des feedbacks après la mise à jour
                        this.fetchFeedbacks();
                    },
                    (error) => {
                        console.error('Error updating feedback:', error);
                    }
                );
        }
    }
}
