import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../../model/offer/offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
  export class OfferService {
    private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiServerUrl}/offer/all`)
  }
  public addOffer(offer: Offer): Observable<Offer> {
    return this.http.post<Offer>(`${this.apiServerUrl}/offer/add`, offer)
  }
  public updateOffer(offer: Offer): Observable<Offer> {
    return this.http.put<Offer>(`${this.apiServerUrl}/offer/update`, offer)
  }
  public deleteOffer(businessKey: String): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/offer/delete/${businessKey}`)
  }

}
