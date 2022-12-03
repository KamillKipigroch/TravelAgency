import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {User} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private apiServerUrl = environment.apiBaseUrl + "/api";

  constructor(private http: HttpClient) {
  }

  public getOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiServerUrl}/offer/get-all`)
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


export class Offer {

  public id: number = 0;

  public country: Country = new Country(0, "");

  public hotel: Hotel[] = [];

  public images:Image[] = [];
  public availabilities:OfferAvailability[] = [];

  public opinions: Opinion[] = [];

  public description: String = "";

  public visible: Boolean = true;
  public price:number = 0;
  public days:number = 0;

}

export class Country {
  public id: number = 0;
  public name: String = "";

  constructor(id: number, name: String) {
    this.id = id;
    this.name = name;
  }
}


export class Room {
  public id:number = 0;
  public visible:Boolean = true;
  public roomDetail:RoomDetails = new RoomDetails(0,"",true)
  public hotel:Hotel = new Hotel(0,"",0,[],0,0,true)
  public description:String = "";
  public quantity:number = 0;
  public price:number = 0;
}

export class RoomDetails {
  public id:number = 0;
  public name:String = "";
  public visible:Boolean = true;
  constructor(id:number, name:String, visible:Boolean) {
    this.id = id;
    this.name = name;
    this.visible = visible;
  }
}

export class Hotel {
  public id: number = 0;
  public name: String = "";
  public standard: number = 0;
  public rooms: Room[] = [];
  public lat = 0.0;
  public lng = 0.0;
  public visible: Boolean = true;

  constructor(id: number, name: String, standard: number, rooms: Room[], lat: number, lng: number, visible: Boolean) {
    this.id = id;
    this.name = name;
    this.standard = standard;
    this.rooms = rooms;
    this.lng = lng;
    this.lat = lat;
    this.visible = visible
  }
}

export class Opinion {
  public id: number = 0;
  public user: User = new User();
  public value: number = 0.0;
  public description: String = "";
  public images: Image[] = [];
  public createDate = new Date();
  public visible = false;
}

export class Image {
  public id: number = 0;
  public name: string = "";
}

export class OfferAvailability {
  public id: number = 0;
  public datetimeStart: Date = new Date();
  public datetimeEnd: Date = new Date();
}
