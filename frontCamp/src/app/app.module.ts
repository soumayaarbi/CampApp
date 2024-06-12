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
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'; // Import HttpClientModule
import { ReservationService } from './reservation/reservation.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ModifyReservationComponent } from './modify-reservation/modify-reservation.component';
import { MatDialogModule } from '@angular/material/dialog';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BoutiqueListComponent } from './boutique-list/boutique-list.component';
import {BoutiqueService} from "./boutique.service";
import { CardComponent } from './card/card.component';
import { AddBoutiqueComponent } from './add-boutique/add-boutique.component';
import { UpdateBoutiqueComponent } from './update-boutique/update-boutique.component';
import { ProductListComponent } from './produit-list/produit-list.component';
import { NgChartsModule } from 'ng2-charts';
import {UpdateProductComponent} from "./update-product/update-product.component";

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
    BoutiqueListComponent,
    CardComponent,
    AddBoutiqueComponent,
    UpdateBoutiqueComponent,
    ProductListComponent,
    UpdateProductComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [ReservationService,BoutiqueService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
