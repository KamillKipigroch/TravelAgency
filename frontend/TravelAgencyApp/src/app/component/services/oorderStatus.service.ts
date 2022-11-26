import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../../model/offer/offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
  export class OrderStatusService {
    private apiServerUrl = environment.apiBaseUrl+"/api/order-status";

  constructor(private http: HttpClient) { }

  public getAll(): Observable<OrderStatus[]> {
    return this.http.get<OrderStatus[]>(`${this.apiServerUrl}/get-all`)
  }
  public add(offer: OrderStatus): Observable<OrderStatus> {
    return this.http.post<OrderStatus>(`${this.apiServerUrl}/add`, offer)
  }
  public update(offer: OrderStatus): Observable<OrderStatus> {
    return this.http.put<OrderStatus>(`${this.apiServerUrl}/update`, offer)
  }
  public disableVisibility(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/disable-visibility`, id)
  }
}

export class OrderStatus{
  public id:number = 0;
  public name:String = "" ;
  public visible:Boolean = true;
}
