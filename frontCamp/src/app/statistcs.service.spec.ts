import { TestBed } from '@angular/core/testing';

import { StatistcsService } from './statistcs.service';

describe('StatistcsService', () => {
  let service: StatistcsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatistcsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
