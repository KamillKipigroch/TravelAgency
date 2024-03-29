import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {OrderStatus} from "./orderStatus.service";
import {OfferAvailability, Room} from "./offer.service";
import {User} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiServerUrl = environment.apiBaseUrl + "/api/order";

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/get-all`)
  }

  public getCustomer(email :String): Observable<Order[]> {
    email = email.toString().replace('@','%40')
    console.log(email)
    return this.http.get<Order[]>(`${this.apiServerUrl}/get-by-user`+'?userEmail='+email)
  }

  public add(newOrder: OrderRequest): Observable<Order> {
    return this.http.post<Order>(`${this.apiServerUrl}/add`, newOrder)
  }

  nextStatus(order: Order) {
    let userEmail = order.user.email;
    let role = order.user.userRole;
    order.user = new User()
    order.user.email = userEmail
    order.user.userRole = role
    return this.http.put<Order>(`${this.apiServerUrl}/next-status`, order)
  }

  cancel(order: Order) {
    let userEmail = order.user.email;
    let role = order.user.userRole;
    order.user = new User()
    order.user.email = userEmail
    order.user.userRole = role
    return this.http.put<Order>(`${this.apiServerUrl}/cancel`, order)
  }
}

export class Order {
  public id: number = 0;
  public orderStatus: OrderStatus = new OrderStatus();
  public deadline: OfferAvailability = new OfferAvailability();
  public room: Room = new Room();
  public user: User = new User();
  public price: number = 0;
}

export class OrderRequest {
  public offerAvailabilityId: number = 0;
  public selectedRoom: number = 0;
  public userEmail: String = "";
}
