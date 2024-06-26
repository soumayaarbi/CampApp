import {CentredeCamp} from "./centredeCamp";
import {Utilisateur} from "./Utilisateur";

export interface Feedback {
    idFeedback: number;
    commentaire: string;
    note: number;
    date: Date;
    centre : CentredeCamp ;
    utilisateur : Utilisateur ;

  }
