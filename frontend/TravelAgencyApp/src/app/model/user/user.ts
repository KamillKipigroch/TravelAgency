export interface IUser {

  iat: number;
  jti: string;
  iss: string;
  aud: string;
  sub: string;
  rol: string[]
  name: string;
  preferred_username: string;
  email: string;
}


