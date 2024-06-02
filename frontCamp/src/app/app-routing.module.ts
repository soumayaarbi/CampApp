import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import {ReclamationFormComponent} from "./reclamation-form/reclamation-form.component";
import { ReclamationListComponent } from './reclamation-list/reclamation-list.component';
import { PiechartComponent } from './piechart/piechart.component';

const routes: Routes = [
  { path: 'reservation', component: ReservationComponent },
  { path: 'reclamation', component: ReclamationFormComponent },
  { path: 'reclamations', component: ReclamationListComponent },
  {path:'pieChart' , component:PiechartComponent}

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
