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

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        SidenavComponent,
        MainComponent,
        ReservationComponent,
        UtilisateurComponent,
        CentreCampingComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FontAwesomeModule,
       
    ]
})
export class AppModule { }
