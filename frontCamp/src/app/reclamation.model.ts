// reclamation.model.ts

export class Reclamation {
    idReclamation: number;
    sujet: string;
    description: string;
    date: Date;
    idUtilisateur: number;
  
    constructor() {
      this.idReclamation = -1;
      this.sujet = '';
      this.description = '';
      this.date = new Date();
      this.idUtilisateur = 1;
    }
  }
  