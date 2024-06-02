import { Component, OnInit } from '@angular/core';
import { Reclamation } from '../reclamation.model';
import { ReclamationService } from '../reclamation.service';

@Component({
  selector: 'app-reclamation-list',
  templateUrl: './reclamation-list.component.html',
  styleUrls: ['./reclamation-list.component.css']
})
export class ReclamationListComponent implements OnInit {
  reclamations: Reclamation[] = [];
  selectedReclamation: Reclamation | null = null; // Réclamation sélectionnée pour modification

  constructor(private reclamationService: ReclamationService) { }

  ngOnInit(): void {
    this.loadReclamations();
  }

  loadReclamations(): void {
    this.reclamationService.getReclamations().subscribe((data: Reclamation[]) => {
      this.reclamations = data;
    });
  }

  deleteReclamation(id: number): void {
    this.reclamationService.deleteReclamation(id).subscribe(() => {
      this.loadReclamations(); // Recharger la liste des réclamations après suppression
    });
  }

  editReclamation(reclamation: Reclamation): void {
    this.selectedReclamation = { ...reclamation }; // Cloner la réclamation pour éviter les effets de bord
  }

  updateReclamation(): void {
    if (this.selectedReclamation) {
      this.reclamationService.updateReclamation(this.selectedReclamation).subscribe(() => {
        this.selectedReclamation = null; // Réinitialiser la réclamation sélectionnée après modification
        this.loadReclamations(); // Recharger la liste des réclamations après modification
      });
    }
  }
}
