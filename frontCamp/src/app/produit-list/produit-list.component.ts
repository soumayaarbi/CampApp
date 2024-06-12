import { Component, OnInit } from '@angular/core';
import { ProduitService } from '../produit.service';
import { ProductStatisticsService } from '../product-statistics.service';
import { Produit } from "../produit.module"; // Ensure this path is correct
import { ProductStatisticsDTO } from '../product-statistics-dto.module'; // Ensure this path is correct
import { ChartData, ChartOptions } from 'chart.js';
import { Router } from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './produit-list.component.html',
  styleUrls: ['./produit-list.component.css']
})
export class ProductListComponent implements OnInit {
  produits: any;
  statistics: ProductStatisticsDTO[] = [];
  boutiqueName: string = "Produit";

  topSellingProducts: ChartData<'bar'> = {
    labels: [],
    datasets: [
      {
        label: 'Vente',
        data: [],
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
      }
    ]
  };

  chartOptions: ChartOptions<'bar'> = {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true
      }
    }
  };

  constructor(
    private produitService: ProduitService,
    private productStatisticsService: ProductStatisticsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchProducts();
    this.fetchStatistics();
  }

  fetchProducts(): void {
    this.produitService.getAllProduits().subscribe(produits => {
      this.produits = produits;
    });
  }

  fetchStatistics(): void {
    this.productStatisticsService.getTopSellingProducts().subscribe(statistics => {
      this.statistics = statistics;
      this.topSellingProducts.labels = statistics.map(stat => stat.nomProduit);
      this.topSellingProducts.datasets[0].data = statistics.map(stat => stat.totalVendu);
    });
  }

  addProduct(produits: Produit): void {
    this.produitService.addProduit(produits).subscribe(() => {
      this.fetchProducts();
    });
  }

  navigateToUpdateProduit(produitId: number): void {
    this.router.navigate(['/update-produit', produitId]);
  }



  deleteProduct(produitId: number): void {
    this.produitService.deleteProduit(produitId).subscribe(() => {
      this.fetchProducts();
    });
  }
}
