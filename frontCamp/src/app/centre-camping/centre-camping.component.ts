import {Component, OnInit} from '@angular/core';
import {CentreService} from "../centre.service";
import {CentredeCamp} from "../centredeCamp";

@Component({
  selector: 'app-centre-camping',
  templateUrl: './centre-camping.component.html',
  styleUrls: ['./centre-camping.component.css']
})
export class CentreCampingComponent implements OnInit{

centree !: CentredeCamp[] ;
  constructor(private centreService : CentreService) {
  }

  ngOnInit(): void {

    this.getPubs();
  }



  getPubs(){
    this.centreService.getAllPublications().subscribe(
        publications => {
          this.centree = publications;
          console.log(publications);
        },
        error => {
          console.error(error);
        }
    );
  }

}
