import { Component, OnInit } from '@angular/core';
import { ActiviteService } from '../activite.service';
import { Activite } from '../activite.model';

@Component({
  selector: 'app-activite-list',
  templateUrl: './activite-list.component.html',
  styleUrls: ['./activite-list.component.css']
})
export class ActiviteListComponent implements OnInit {
  activites: Activite[] = [];
  editMode: boolean = false;
  activiteToEdit: Activite | null = null;

  constructor(private activiteService: ActiviteService) { }

  ngOnInit(): void {
    this.loadActivites();
  }

  loadActivites(): void {
    this.activiteService.getAllActivites().subscribe(
      (activites: Activite[]) => {
        this.activites = activites;
      },
      (error) => {
        console.error('An error occurred while loading activities:', error);
      }
    );
  }

  deleteActivite(id: number): void {
    this.activiteService.deleteActivite(id).subscribe(
      () => {
        // Remove the activity from the list after successful deletion
        this.activites = this.activites.filter(activite => activite.idActivite !== id);
      },
      (error) => {
        console.error('An error occurred while deleting the activity:', error);
      }
    );
  }

  editActivite(activite: Activite): void {
    this.editMode = true;
    this.activiteToEdit = { ...activite }; // Clone the activite to avoid direct mutation
  }

  updateActivite(): void {
    if (this.activiteToEdit) {
      this.activiteService.modifyActivite(this.activiteToEdit).subscribe(
        (updatedActivite: Activite) => {
          // Update the activite in the list
          const index = this.activites.findIndex(a => a.idActivite === updatedActivite.idActivite);
          if (index !== -1) {
            this.activites[index] = updatedActivite;
          }
          // Exit edit mode
          this.editMode = false;
          this.activiteToEdit = null;
        },
        (error) => {
          console.error('An error occurred while updating the activity:', error);
        }
      );
    }
  }

  cancelEdit(): void {
    this.editMode = false;
    this.activiteToEdit = null;
  }

  get editNom(): string {
    return this.activiteToEdit?.nom ?? '';
  }

  set editNom(value: string) {
    if (this.activiteToEdit) {
      this.activiteToEdit.nom = value;
    }
  }

  get editTarif(): number {
    return this.activiteToEdit?.tarif ?? 0;
  }

  set editTarif(value: number) {
    if (this.activiteToEdit) {
      this.activiteToEdit.tarif = value;
    }
  }

  get editNbrPersonne(): number {
    return this.activiteToEdit?.nbrPersonne ?? 0;
  }

  set editNbrPersonne(value: number) {
    if (this.activiteToEdit) {
      this.activiteToEdit.nbrPersonne = value;
    }
  }

  get editDescription(): string {
    return this.activiteToEdit?.description ?? '';
  }

  set editDescription(value: string) {
    if (this.activiteToEdit) {
      this.activiteToEdit.description = value;
    }
  }
}
