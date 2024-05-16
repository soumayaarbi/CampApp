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
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { ReservationService } from './reservation/reservation.service';
import { Reservation } from './Models/reservation';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ModifyReservationComponent } from './modify-reservation/modify-reservation.component'; // Ensure correct path to Reservation model
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
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
    FormsModule
  ],
})
export class AppModule {}
