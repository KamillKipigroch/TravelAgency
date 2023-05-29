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

  private uploadImageData :any;

  constructor(private http: HttpClient) {
  }

  public getOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiServerUrl}/offer/get-all`)
  }
  public getRecommendedOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiServerUrl}/offer/get-recommended`)
  }

  public getLastMinuteOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiServerUrl}/offer/get-last-minute`)
  }

  public getOfferById(id: number): Observable<Offer> {
    return this.http.get<Offer>(`${this.apiServerUrl}/offer/find/` + id)
  }

  public getCountry(): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.apiServerUrl}/country/get-all`)
  }

  public addOffer(offer: Offer): Observable<Offer> {
    return this.http.post<Offer>(`${this.apiServerUrl}/offer/add`, offer)
  }

  public updateOffer(offer: Offer): Observable<Offer> {
    return this.http.put<Offer>(`${this.apiServerUrl}/offer/update`, offer)
  }

  public uploadRoomImage(file:File, roomId:number): Observable<Image> {
    this.uploadImageData = new FormData();
    this.uploadImageData.append('image', file, file.name);
    this.uploadImageData.append('room', roomId.toString());

    return this.http.post<Image>(`${this.apiServerUrl}/image/upload-room-image`, this.uploadImageData)
  }

  public uploadOfferImage(file:File, offerId:number): Observable<Image> {
    this.uploadImageData = new FormData();
    this.uploadImageData.append('image', file, file.name);
    this.uploadImageData.append('offer', offerId.toString());

    return this.http.post<Image>(`${this.apiServerUrl}/image/upload-offer-image`, this.uploadImageData)
  }

  public deleteOffer(businessKey: String): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/offer/delete/${businessKey}`)
  }

}


export class Offer {

  public id: number = 0;

  public hotel: Hotel[] = [];

  public images: Image[] = [];

  public availabilities: OfferAvailability[] = [];

  public selectedAvailabilities: OfferAvailability[] = [];

  public opinions: Opinion[] = [];

  public description: String = "";

  public visible: Boolean = true;

  public price: number = 0;

  public days: number = 0;

  public promotionPrice: number = 0;

  constructor(id: number, country: Country, hotel: Hotel[], images: Image[], availabilities: OfferAvailability[], opinions: Opinion[], description: String,
              visible: boolean, price: number, days: number, promotionPrice: number) {
    this.id = id;

    this.hotel = hotel;

    this.images = images;

    this.availabilities = availabilities;

    this.selectedAvailabilities = availabilities;

    this.opinions = opinions;

    this.description = description;

    this.visible = visible;

    this.price = price;

    this.days = days;

    this.promotionPrice = promotionPrice;
  }

}

export class Country {
  public id: number = 0;
  public name: String = "";
  public code: String = "";

  constructor(id: number, name: String, code: String) {
    this.id = id;
    this.name = name;
    this.code = code;
  }
}


export class Room {
  public id: number = 0;
  public visible: Boolean = true;
  public roomDetail: RoomDetails = new RoomDetails(0, "", true)
  public hotel: Hotel = new Hotel()
  public roomImage: Image[] = [];
  public roomImageFile: File[] = [];
  public description: String = "";
  public quantity: number = 0;
  public selected: boolean = false;
  public price: number = 0;
}

export class RoomDetails {
  public id: number = 0;
  public name: String = "";
  public visible: Boolean = true;

  constructor(id: number, name: String, visible: Boolean) {
    this.id = id;
    this.name = name;
    this.visible = visible;
  }
}

export class Hotel {
  public id: number = 0;
  public name: String = "";
  public standard: number = 0;
  public country: Country = new Country(0, "", "");
  public rooms: Room[] = [];
  public lat = 0.0;
  public lng = 0.0;
  public visible: Boolean = true;

}

export class Opinion {
  public id: number = 0;
  public user: User = new User();
  public value: number = 0.0;
  public header: String = "";
  public description: String = "";
  public opinionImages: Image[] = [];
  public createDate = new Date();
  public visible = false;
}

export class Image {
  public id: number = 0;
  public url: string = "";
}

export class OfferAvailability {
  public id: number = 0;
  public rangeDate: any = [];
  public datetimeStart: Date = new Date();
  public datetimeEnd: Date = new Date();
  public price:number = 10;
  public promotion: boolean = false;
  public promotionPrice: number = 0;
}
