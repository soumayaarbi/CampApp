import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from '../feedbacks.model';
import { FeedbackService } from '../feedbacks.service';
import {ReactionService} from "../reaction.service";
import {Reaction} from "../Reaction";
import {Utilisateur} from "../Utilisateur";
import {CentredeCamp} from "../centredeCamp";

@Component({
  selector: 'app-feedback-list',
  templateUrl: './feedback-list.component.html',
  styleUrls: ['./feedback-list.component.css']
})
export class FeedbackListComponent implements OnInit {
  feedbacks: Feedback[] = [];
  selectedFeedback: Feedback | null = null; // Initialisé à null par défaut
  feedback: Feedback = { idFeedback: -1, commentaire: '', note: 0, date: new Date()  , centre : new CentredeCamp() , utilisateur : new Utilisateur()};
  pubId !: number ;
  reactionsListLike !: Reaction[];
  reactionsListDisLike !: Reaction[];
  reaction !: Reaction ;
  constructor(private feedbackService: FeedbackService, private router: Router,private reactionService: ReactionService) { }


  ngOnInit(): void {
    this.fetchFeedbacks();
      // this.feedbackService.getPublication(this.pubId)
      //   .subscribe(data =>  this.feedback = data)

    // this.likes();
    // this.dislikes();
  }

  onSubmit(): void {
    this.feedback.date = new Date() ;
    this.feedbackService.addFeedback(this.feedback)
      .subscribe(() => {
        console.log('Feedback added successfully');
        window.location.reload();
        // Optionnellement, naviguer vers une autre page ou effectuer d'autres actions après la soumission réussie
      }, error => {
        console.error('Error adding feedback:', error);
        // Gérer l'erreur
      });
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

  updateFeedback(): void {
    if (this.selectedFeedback) {
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






  // likes(): void{
  //   this.reactionService.getReactionsLikeParPublication(this.pubId).subscribe(
  //     reactions => {
  //       this.reactionsListLike = reactions;
  //       console.log(reactions);
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //
  //   );
  // }
  //
  // dislikes():void{
  //   this.reactionService.getReactionsDisLikeParPublication(this.pubId).subscribe(
  //     reactions => {
  //       this.reactionsListDisLike = reactions;
  //       console.log(reactions);
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //
  //   );
  // }


  // saveReactionLike(idUser: number, idPub: number){
  //   this.reaction.utilisateur = new Utilisateur();
  //   this.reaction.feedbacks = this.feedbacks ;
  //   this.reaction.utilisateur.idUtilisateur = 1;
  //   this.reaction.feedbacks.idFeedback = idPub;
  //   this.reaction.reactionType ="LIKE";
  //   this.reactionService.saveReaction(this.reaction).subscribe(
  //
  //     (res) => {
  //       console.log("succès", this.reaction);
  //       // this.likes();
  //       // this.dislikes();
  //     },
  //     (err) => {
  //       console.log(err);
  //     }
  //   )
  // }
  // saveReactionDisLike(idUser: number, idPub: number){
  //   this.reaction.utilisateur = new Utilisateur();
  //   // this.reaction.publication = new Publication();
  //   this.reaction.utilisateur.idUtilisateur = idUser;
  //   this.reaction.feedbacks.idFeedback = idPub;
  //   this.reaction.reactionType ="DISLIKE";
  //   this.reactionService.saveReaction(this.reaction).subscribe(
  //
  //     (res) => {
  //       console.log("succès", this.reaction);
  //       // this.likes();
  //       // this.dislikes();
  //     },
  //     (err) => {
  //       console.log(err);
  //     }
  //   )
  // }















}
