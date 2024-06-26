  // forum-add.component.ts

  import {Component, EventEmitter, Output} from '@angular/core';
  import { Forum } from '../forum.model'; // Renommer le modèle en forum.model
  import { ForumService } from '../forum.service'; // Renommer le service en forum.service

  @Component({
    selector: 'app-forum-add', // Renommer le sélecteur
    templateUrl: './forum-add.component.html', // Renommer le fichier de modèle
    styleUrls: ['./forum-add.component.css'] // Renommer le fichier de style
  })
  export class ForumAddComponent {
    forum: Forum = { idForum: -1, sujet: '', message: '' }; // Renommer la variable

    @Output() forumAdded: EventEmitter<void> = new EventEmitter<void>();

    constructor(private forumService: ForumService) {} // Renommer le service

    onSubmit(): void {
      this.forumService.addForum(this.forum)
        .subscribe(() => {
          console.log('Forum added successfully');
          // Optionnellement, naviguer vers une autre page ou effectuer d'autres actions après la soumission réussie
        }, error => {
          console.error('Error adding forum:', error);
          window.location.reload();
          this.forumAdded.emit();
          // Gérer l'erreur
        });
    }
  }
