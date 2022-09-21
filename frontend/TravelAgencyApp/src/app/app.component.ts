import {Component, OnInit} from '@angular/core';
import {Offer} from "./offer";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {OfferService} from "./offer.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'TravelAgencyApp';

  public offers: Offer[] | undefined;

  constructor(private offerService: OfferService) { }

  ngOnInit() {
    this.getOffers();
  }

  public getOffers():void {
    this.offerService.getOffers().subscribe(
      (response:Offer[]) => {
        this.offers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}