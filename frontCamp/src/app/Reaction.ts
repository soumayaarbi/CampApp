import {Feedback} from "./feedbacks.model";
import {Utilisateur} from "./Utilisateur";


export class Reaction {
  idReact !: number;
  reactionType !:string;
  utilisateur !: Utilisateur;
  publication !: Feedback;
  feedbacks !: any;

}
