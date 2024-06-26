import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import {ReclamationFormComponent} from "./reclamation-form/reclamation-form.component";
import { ReclamationListComponent } from './reclamation-list/reclamation-list.component';
import { PiechartComponent } from './piechart/piechart.component';
import {ForumListComponent} from "./forum-list/forum-list.component";
import {ForumAddComponent} from "./forum-add/forum-add.component";
import {FeedbackListComponent} from "./feedback-list/feedback-list.component";
import {FeedbackAddComponent} from "./feedback-add/feedback-add.component";
import {CentreCampingComponent} from "./centre-camping/centre-camping.component";
import {FeedbackCentreComponent} from "./feedback-centre/feedback-centre.component";

const routes: Routes = [
  { path: 'reservation', component: ReservationComponent },
  { path: 'reclamation', component: ReclamationFormComponent },
  { path: 'reclamations', component: ReclamationListComponent },
  {path:'pieChart' , component:PiechartComponent},
  { path: 'forum', component: ForumListComponent },
  { path: 'forum/add', component: ForumAddComponent },
  { path: 'feedback/centre/:id', component: FeedbackCentreComponent },
  { path: 'centre/list', component: CentreCampingComponent },
  { path: 'feedback', component: FeedbackListComponent },
  { path: 'feedback/add', component: FeedbackAddComponent },
  { path: '', redirectTo: '/forum', pathMatch: 'full' },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
