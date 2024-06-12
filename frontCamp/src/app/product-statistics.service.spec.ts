import { TestBed } from '@angular/core/testing';

import { ProductStatisticsService } from './product-statistics.service';

describe('ProductStatisticsService', () => {
  let service: ProductStatisticsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductStatisticsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
