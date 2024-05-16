import { Component } from '@angular/core';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css'],
})
export class AddReservationComponent {
  isWindowOpen = true; // Variable pour contrôler la visibilité de la fenêtre

  closeWindow() {
    this.isWindowOpen = false;
  }
}
