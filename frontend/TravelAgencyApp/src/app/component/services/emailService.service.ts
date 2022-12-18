import {Injectable} from '@angular/core';
import {HttpClient, HttpStatusCode} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Offer, Opinion} from "./offer.service";
import {User} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private apiServerUrl = environment.apiBaseUrl + "/api";

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.apiServerUrl}/mail/get-all`)
  }

  public addQuestion(mail: Question): Observable<HttpStatusCode> {
    return this.http.post<HttpStatusCode>(`${this.apiServerUrl}/mail/add`, mail)
  }

  public answerToQuestion(mail: Question): Observable<Question> {
    return this.http.put<Question>(`${this.apiServerUrl}/mail/update`, mail)
  }

}
export class Question {
  id: number = 0;
  email: String = '';
  message: String = '';
  employee: User = new User();
  answerMessage: String = '';
  answered: boolean = false;
}


