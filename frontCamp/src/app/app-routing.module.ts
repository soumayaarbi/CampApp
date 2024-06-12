import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import { BoutiqueListComponent } from './boutique-list/boutique-list.component';
import { CardComponent } from "./card/card.component";
import {AddBoutiqueComponent} from "./add-boutique/add-boutique.component";
import {UtilisateurComponent} from "./utilisateur/utilisateur.component";
import {UpdateBoutiqueComponent} from "./update-boutique/update-boutique.component";
import {ProductListComponent} from "./produit-list/produit-list.component";
import {UpdateProductComponent} from "./update-product/update-product.component";

const routes: Routes = [
  { path: 'reservation', component: ReservationComponent },
  { path: 'boutiques', component: BoutiqueListComponent },
  { path: 'card', component: CardComponent },
  { path: 'add-boutique', component: AddBoutiqueComponent },
  { path: 'utilisateur', component: UtilisateurComponent },
  { path: 'update-boutique/:id', component: UpdateBoutiqueComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'update-produit/:id', component: UpdateProductComponent },
  { path: '', component: MainComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
