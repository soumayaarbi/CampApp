
import {Boutique} from "./boutique.model";



export interface Produit {
  idProduit: number;
  nom: string;
  prix: number;
  quantite: number;
  description: string;
  promotion: number;
  boutique: Boutique;
}
