export interface Reservation {
  id: number;
  dateArrivee: Date;
  dateSortie: Date;
  nbrPersonne: number;
  lieux: string;
  idHebergement: number;
  idEquipement: number;
  idCentre: number;
  idUtilisateur: number;
}
