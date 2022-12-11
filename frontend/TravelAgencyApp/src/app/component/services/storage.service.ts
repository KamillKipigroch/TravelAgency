import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
import {Offer} from "./offer.service";

const USER_KEY = 'auth-user';
const OFFER_ID = 'offer-id';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveOffer(offerId: any): void{
    window.sessionStorage.removeItem(OFFER_ID)
    window.sessionStorage.setItem(OFFER_ID, offerId)
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

  public getOfferId(): any {
    return window.sessionStorage.getItem(OFFER_ID);
  }

  public isLoggedIn(): boolean {
    const token = window.sessionStorage.getItem(USER_KEY);
    return !!token;
  }

  public logout():any {
    window.sessionStorage.removeItem(USER_KEY);
  }
}
