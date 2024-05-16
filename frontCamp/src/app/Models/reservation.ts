
export class Reservation {
  
  constructor(
      public idReservation?: number,
  public dateArrivee?: Date,
  public dateSortie?: Date,
 public nbrPersonne?: number,
  public lieux?: string,
  public idHebergement?: number,
  public idEquipement?: number,
  public idCentre?: number
  
  ){}

}
