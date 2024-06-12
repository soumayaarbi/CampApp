import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BoutiqueService } from '../boutique.service';
import { Boutique } from '../boutique.model';

@Component({
  selector: 'app-boutique-list',
  templateUrl: './boutique-list.component.html',
  styleUrls: ['./boutique-list.component.css']
})
export class BoutiqueListComponent implements OnInit {
  boutiques: any;
  searchResult: Boutique | null = null;
  searchError: string | null = null;

  constructor(private boutiqueService: BoutiqueService, private router: Router) { }

  ngOnInit(): void {
    this.fetchBoutiques();
  }

  fetchBoutiques(): void {
    this.boutiqueService.getAllBoutiques().subscribe(
      res => {
        console.log(res);
        this.boutiques = res;
      },
      error => {
        console.error('Erreur lors de la récupération des boutiques :', error);
      }
    );
  }

  searchBoutiqueByNom(nom: string): void {
    this.boutiqueService.getBoutiqueByNom(nom).subscribe(
      res => {
        this.searchResult = res;
        this.searchError = null;
      },
      error => {
        this.searchResult = null;
        this.searchError = 'Boutique non trouvée';
        console.error('Erreur lors de la recherche de la boutique :', error);
      }
    );
  }
  navigateToUpdateBoutique(boutiqueId: number): void {
    this.router.navigate(['/update-boutique', boutiqueId]);
  }

  deleteBoutique(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette boutique ?')) {
      this.boutiqueService.deleteBoutique(id).subscribe(
        () => {
          console.log('Boutique supprimée avec succès!');
          this.fetchBoutiques();
        },
        error => {
          console.error('Erreur lors de la suppression de la boutique :', error);
        }
      );
    }
}
}
