import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { Router } from '@angular/router';
import { Forum } from '../forum.model';
import { ForumService } from '../forum.service';

@Component({
  selector: 'app-forum-list',
  templateUrl: './forum-list.component.html',
  styleUrls: ['./forum-list.component.css']
})
export class ForumListComponent implements OnInit {
  forums: Forum[] = [];
  selectedForum: Forum | null = null; // Initialisé à null par défaut
  forum: Forum = { idForum: -1, sujet: '', message: '' };
  @Output() forumAdded: EventEmitter<void> = new EventEmitter<void>();
  constructor(private forumService: ForumService, private router: Router) { }

  ngOnInit(): void {
    this.fetchForums();
  }

  onSubmit(): void {
    this.forumService.addForum(this.forum)
      .subscribe(() => {
        console.log('Forum added successfully');
        window.location.reload();
        // Optionnellement, naviguer vers une autre page ou effectuer d'autres actions après la soumission réussie
      }, error => {
        console.error('Error adding forum:', error);
        window.location.reload();
        this.forumAdded.emit();
        // Gérer l'erreur
      });
  }

  fetchForums(): void {
    this.forumService.getAllForum()
      .subscribe(
        (forums: Forum[]) => {
          this.forums = forums;
        },
        (error) => {
          console.error('Une erreur s\'est produite lors de la récupération des forums :', error);
        }
      );
  }

  onForumAdded(): void {
    this.fetchForums(); // Mettre à jour la liste des forums après l'ajout
  }


  deleteForum(forumId: number): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer ce forum ?")) {
      this.forumService.deleteForum(forumId)
        .subscribe(
          () => {
            this.forums = this.forums.filter(f => f.idForum !== forumId);
          },
          (error) => {
            console.error('Une erreur s\'est produite lors de la suppression du forum :', error);
          }
        );
    }
  }

  editForum(forum: Forum): void {
    this.selectedForum = forum;
  }

  updateForum(): void {
    if (this.selectedForum) {
      this.forumService.updateForum(this.selectedForum)
        .subscribe(
          (updatedForum: Forum) => {
            console.log('Forum updated successfully:', updatedForum);
            // Réinitialiser le forum sélectionné
            this.selectedForum = null;
            // Rechargez la liste des forums après la mise à jour
            this.fetchForums();
          },
          (error) => {
            console.error('Error updating forum:', error);
          }
        );
    }
  }
}
