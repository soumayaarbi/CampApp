import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontCamp';

  constructor(private router: Router) {}
  goToForums(): void {
    this.router.navigate(['/forums']);
  }

  goToFeedbacks(): void {
    this.router.navigate(['/feedbacks']);
  }
  onForumAdded(event: any): void {
    // Traitement à effectuer lorsqu'un forum est ajouté
    console.log('Forum added:', event);
  }
}
