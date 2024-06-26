import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackCentreComponent } from './feedback-centre.component';

describe('FeedbackCentreComponent', () => {
  let component: FeedbackCentreComponent;
  let fixture: ComponentFixture<FeedbackCentreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackCentreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackCentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
