import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductStatisticsDTO } from '../app/product-statistics-dto.module';

@Injectable({
  providedIn: 'root'
})
export class ProductStatisticsService {
  private baseUrl = 'http://localhost:8084/api/statistics';

  constructor(private http: HttpClient) {}

  getTopSellingProducts(): Observable<ProductStatisticsDTO[]> {
    return this.http.get<ProductStatisticsDTO[]>(`${this.baseUrl}/top-selling-products`);
  }
}
