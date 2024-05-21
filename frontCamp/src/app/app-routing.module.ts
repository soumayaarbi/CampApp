import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';

const routes: Routes = [
  { path: 'reservation', component: ReservationComponent },
  { path: '', component: MainComponent },
  { path: 'add-reservation', component: AddReservationComponent },
  { path: '', redirectTo: '/reservations', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
