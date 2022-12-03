import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
  export class UserService {
    private apiServerUrl = environment.apiBaseUrl+"/api/user";

  constructor(private http: HttpClient) { }

  public getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/get-all-users`)
  }

  public getAllEmployee(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/get-all-employers`)
  }
  public add(offer: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/add-user`, offer)
  }

  public lock(user:User): Observable<any> {
    return this.http.put<User>(`${this.apiServerUrl}/lock-user`, user)
  }
  public unlock(user:User): Observable<any> {
    return this.http.put<User>(`${this.apiServerUrl}/unlock-user`, user)
  }
  public enable(user:User): Observable<any> {
    return this.http.put<User>(`${this.apiServerUrl}/enable-user`, user)
  }
}

export class User{
  public id:number = 0;
  public firstName:String = "" ;
  public lastName:String = "" ;
  public userRole:String = "" ;
  public email:String = "" ;
  public password:String = "";
  public image:String = "" ;
  public locked:Boolean = false;
  public enabled:Boolean = true;
}

export class AddUser{
  public firstName:String = "" ;
  public lastName:String = "" ;
  public email:String = "" ;
  public userRole:String = "" ;
}
