import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
import {Offer} from "./offer.service";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, token);
  }

  public getUser(): any {
    const token = window.sessionStorage.getItem(USER_KEY);
    if (token) {
      return jwt_decode(token);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    const token = window.sessionStorage.getItem(USER_KEY);
    if (token) {
      return true;
    }

    return false;
  }

  public logout():any {
    window.sessionStorage.removeItem(USER_KEY);
  }
}
