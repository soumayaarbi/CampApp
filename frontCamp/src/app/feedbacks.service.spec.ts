import { TestBed } from '@angular/core/testing';

import { FeedbackService } from './feedbacks.service';

describe('FeedbacksService', () => {
  let service: FeedbackService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeedbackService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
