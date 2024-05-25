import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { MainComponent } from './main/main.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReservationComponent } from './reservation/reservation.component';
import { UtilisateurComponent } from './utilisateur/utilisateur.component';
import { CentreCampingComponent } from './centre-camping/centre-camping.component';
import { ReservationService } from './reservation/reservation.service';
import { Reservation } from './Models/reservation';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ModifyReservationComponent } from './modify-reservation/modify-reservation.component'; // Ensure correct path to Reservation model
import { MatDialogModule } from '@angular/material/dialog';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';


import { MainResponsableComponent } from './main-responsable/main-responsable.component';
import { SidenavResponsableComponent } from './sidenav-responsable/sidenav-responsable.component';
import { MainCampeurComponent } from './main-campeur/main-campeur.component';
import { SidenavCampeurComponent } from './sidenav-campeur/sidenav-campeur.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidenavComponent,
    MainComponent,
    ReservationComponent,
    UtilisateurComponent,
    CentreCampingComponent,
    AddReservationComponent,
    ModifyReservationComponent,
    AppComponent,
    HeaderComponent,
    SidenavComponent,
    MainComponent,
    ReservationComponent,
    UtilisateurComponent,
    CentreCampingComponent,
    SigninComponent,
    SignupComponent,
    MainResponsableComponent,
    SidenavResponsableComponent,
    MainCampeurComponent,
    SidenavCampeurComponent,
  ],
  providers: [ReservationService],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
  
      
       

    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule
  ]

})
export class AppModule{}




