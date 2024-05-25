import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';

import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { UtilisateurComponent } from './utilisateur/utilisateur.component';
import { MainCampeurComponent } from './main-campeur/main-campeur.component';


const routes: Routes = [
  { path: 'reservation', component: ReservationComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'main', component: MainComponent },
  { path: 'profile', component: UtilisateurComponent },
  { path: '', component: SigninComponent },
  { path: 'addreservation', component: AddReservationComponent },
  { path: 'maincampeur', component: MainCampeurComponent },
  { path: 'mainresponsable', component: MainCampeurComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
