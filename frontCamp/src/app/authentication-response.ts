// authentication-response.ts
export interface AuthenticationResponse {
    [x: string]: string;
    token: string;
    message: string;
    role: string;
    username:string;
  }
  