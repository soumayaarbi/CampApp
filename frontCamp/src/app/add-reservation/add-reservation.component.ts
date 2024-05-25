import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation/reservation.service';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css'],
})
export class AddReservationComponent implements OnInit {
  selectedLocation: string = '';
  selectedCentre: any = null; // Utiliser any pour conserver l'objet complet
  availableLocations: string[] = [];
  availableCentres: any[] = [];
  availableEquipments: any[] = [];
  availableAccommodations: any[] = [];
  step: number = 1;
  selectedEquipment: string = '';

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadLocations();
  }

  loadLocations() {
    this.reservationService.getAllLieux().subscribe(
      (locations) => {
        this.availableLocations = locations;
      },
      (error) => {
        console.error('Failed to load locations:', error);
      }
    );
  }

  loadCentresByLocation() {
    this.reservationService
      .getCentresByLocation(this.selectedLocation)
      .subscribe(
        (centres) => {
          this.availableCentres = centres;
          this.step = 2;
        },
        (error) => {
          console.error('Failed to load centres:', error);
        }
      );
  }

  selectCentre(centre: any) {
    this.selectedCentre = centre;
  }

  loadEquipmentsAndAccommodationsByCentre() {
    if (
      this.selectedCentre &&
      typeof this.selectedCentre.idCentre === 'number'
    ) {
      console.log('Selected centre:', this.selectedCentre);

      const centreId = this.selectedCentre.idCentre;

      // Utilisez centreId dans les requêtes GET
      this.reservationService
        .getEquipmentsByCentre(centreId.toString())
        .subscribe(
          (equipments) => {
            this.availableEquipments = equipments;

            // Vérifier si les équipements sont chargés avec succès
            if (this.availableAccommodations.length > 0) {
              this.step = 4; // Activer la Step 4 uniquement lorsque les équipements et les hébergements sont chargés
            }
          },
          (error) => {
            console.error('Failed to load equipments:', error);
          }
        );
      this.reservationService
        .getAccommodationsByCentre(centreId.toString())
        .subscribe(
          (accommodations) => {
            this.availableAccommodations = accommodations;

            // Vérifier si les hébergements sont chargés avec succès
            if (this.availableEquipments.length > 0) {
              this.step = 4; // Activer la Step 4 uniquement lorsque les équipements et les hébergements sont chargés
            }
          },
          (error) => {
            console.error('Failed to load accommodations:', error);
          }
        );
    } else {
      console.error('Selected centre is not valid');
    }
  }
}
