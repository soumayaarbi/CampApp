export interface Reservation {
status: any;
  idReservation: number;
  dateArrivee: Date;
  dateSortie: Date;
  nbrPersonne: number;
  lieux: string;
  hebergement: Hebergement;
  equipement: Equipement;
  centreDeCamping: CentreDeCamping;
  utilisateur: Utilisateur;
}

export interface Hebergement {
  // Propriétés de l'hébergement
}

export interface Equipement {
  // Propriétés de l'équipement
}

export interface CentreDeCamping {
  // Propriétés du centre de camping
}

export interface Utilisateur {
  // Propriétés de l'utilisateur
}
