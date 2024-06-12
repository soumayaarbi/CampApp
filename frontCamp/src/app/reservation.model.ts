import {Boutique} from "./boutique.model";
import {Produit} from "./produit.module";

export interface Reservation {
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
  idUtilisateur?: number; // Clé primaire (facultative car sera générée par le backend)
  nom?: string;
  prenom?: string;
  email?: string;
  motDePasse?: string;
  datedenais?: Date;
  adresse?: string;
  role?: Role;
  boutiques?: Boutique[]; // Relation avec Boutique
  panier?: Panier; // Relation avec Panier (facultative)
  commandes?: Commande[]; // Relation avec Commande
}

export enum Role {
  ADMINISTRATEUR = 'ADMINISTRATEUR',
  RESPENSABLECENTRE = 'RESPENSABLECENTRE',
  CAMPEUR ='CAMPEUR'
}
export interface Panier {
  id?: number; // Clé primaire (facultative car sera générée par le backend)
  client?: Utilisateur; // Relation avec Utilisateur (client)
  produits?: Produit[]; // Relation avec Produit (table de jonction)
}
export interface Commande {
  idCommande?: number; // Clé primaire (facultative car sera générée par le backend)
  quantite: number;
  tarif: number;
  adresse: string;
  client?: Utilisateur; // Relation avec Utilisateur (client)
  produits?: Produit[]; // Relation avec Produit (table de jonction)
}
