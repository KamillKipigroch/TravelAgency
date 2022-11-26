import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../../model/offer/offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
  export class RoomDetailService {
    private apiServerUrl = environment.apiBaseUrl+"/api/room-detail";

  constructor(private http: HttpClient) { }

  public getAll(): Observable<RoomDetail[]> {
    return this.http.get<RoomDetail[]>(`${this.apiServerUrl}/get-all`)
  }
  public add(offer: RoomDetail): Observable<RoomDetail> {
    return this.http.post<RoomDetail>(`${this.apiServerUrl}/add`, offer)
  }
  public update(offer: RoomDetail): Observable<RoomDetail> {
    return this.http.put<RoomDetail>(`${this.apiServerUrl}/update`, offer)
  }
  public disableVisibility(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/disable-visibility`, id)
  }
}

export class RoomDetail{
  public id:number = 0;
  public name:String = "" ;
  public visible:Boolean = true;
}
