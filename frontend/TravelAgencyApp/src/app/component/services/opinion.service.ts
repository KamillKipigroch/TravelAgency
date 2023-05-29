import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Opinion} from "./offer.service";

@Injectable({
  providedIn: 'root'
})
export class OpinionService {
  private apiServerUrl = environment.apiBaseUrl + "/api";

  constructor(private http: HttpClient) {
  }

  public addOpinion(opinion: OpinionRequest): Observable<Opinion> {
    return this.http.post<Opinion>(`${this.apiServerUrl}/opinion/add`, opinion)
  }

  public uploadImage(opinionId: number, file: File): Observable<Image> {
    const uploadImageData = new FormData();
    uploadImageData.append('image', file, file.name);
    uploadImageData.append('opinion', opinionId.toString());
    return this.http.post<Image>(`${this.apiServerUrl}/image/upload-opinion-image`, uploadImageData)
  }

}

export class OpinionRequest {
  public userEmail: String = '';
  public offerId: number = 0;
  public value: number = 0.0;
  public image: ImageRequest[] = [];
  public header: String = '';
  public description: String = '';
  public visible = false;
}


export class ImageRequest {
  url: String = '';
}

export class Image {
  id: number = 0;
  url: String = '';
}


