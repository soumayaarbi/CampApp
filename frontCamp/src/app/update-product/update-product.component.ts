import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from '../produit.service';
import { Produit } from '../produit.module';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  updateProduitForm: FormGroup;
  produitId: number;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private produitService: ProduitService
  ) {
    this.updateProduitForm = this.formBuilder.group({
      nomProduit: ['', Validators.required],
      prix: ['', Validators.required],
      quantite: ['', Validators.required],
      promotion: ['', Validators.required],
      description: ['', Validators.required]
    });
    this.produitId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.loadProduitDetails();
  }

  loadProduitDetails(): void {
    this.produitService.getProduitById(this.produitId).subscribe(
      (produit) => {
        this.updateProduitForm.patchValue(produit);
      },
      (error) => {
        console.error('Erreur lors de la récupération des détails du produit', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateProduitForm.invalid) {
      return;
    }

    const updatedProduit: Produit = {
      ...this.updateProduitForm.value,
      id: this.produitId
    };



    this.produitService.updateProduit(this.produitId,updatedProduit).subscribe(
      (response) => {
        console.log('Produit mis à jour avec succès!', response);
        this.router.navigate(['/products']);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du produit', error);
      }
    );
  }
}
