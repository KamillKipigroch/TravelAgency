import {Component, OnInit} from '@angular/core';
import {Offer} from "./model/offer/offer";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {OfferService} from "./model/offer/offer.service";
import { PrimeNGConfig } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'TravelAgencyApp';

  public offers: Offer[] | undefined;

  constructor(private offerService: OfferService,private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
    this.primengConfig.ripple = true;
    this.initOffers();
  }

  public initOffers():void {
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
