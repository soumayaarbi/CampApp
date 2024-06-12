import {Utilisateur} from "./reservation.model";
import {Produit} from "./produit.module";

export interface Boutique {
  idBoutique?: number;
  nomBoutique: string;
  dateCreation: Date;
  owner: string;
  utilisateur?: Utilisateur; // Utilisateur associé à cette boutique
  produits: Produit[]; // Produits associés à cette boutique
  description: string;
}

