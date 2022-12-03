import { Component, OnInit } from '@angular/core';
import {Offer, OfferService} from "../../../services/offer.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MessageService, SelectItem} from "primeng/api";

@Component({
  selector: 'app-offer-component',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent implements OnInit {

  offers: Offer[] = [];

  sortOrder: number = 0;

  sortField: string = "";
  more:boolean =false;
  number:number = 3;

  constructor(private offerService: OfferService) { }

  ngOnInit() {
    this.offerService.getOffers().subscribe(
      (response: Offer[]) => {
        this.offers = response;
        this.offers.forEach(offer =>{
          offer.days = offer.availabilities[0].datetimeEnd.getTime() - offer.availabilities[0].datetimeStart.getTime()
          console.log(offer.days)
          offer.price = offer.hotel[0].rooms[0].price * offer.days;
        })
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      });

  }

  clickMore(){
    this.number += 5;
    console.log(this.number)
  }

  onSortChange(event: any) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }
}
