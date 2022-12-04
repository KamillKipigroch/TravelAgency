import {Component, OnChanges, OnInit} from '@angular/core';
import {Country, Offer, OfferService} from "../../../services/offer.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-offer-component',
  templateUrl: './offerlm.component.html',
  styleUrls: ['./offerlm.component.css']
})
export class OfferLastMinuteComponent implements OnInit, OnChanges {

  offers: Offer[] = [];
  all: Offer[] = [];

  sortOrder: number = 0;

  sortField: string = "";
  more: boolean = false;
  number: number = 3;
  readonly: boolean = true;
  selectedCountries: Country[] = [];
  countries: Country[] = [];
  rangeDates: Date[] = [];
  millisecondsPerDay = 1000 * 60 * 60 * 24;
  people: number = 1;
  todayDate = new Date();

  ngOnChanges() {
    this.ngOnInit();
  }

  constructor(private offerService: OfferService) {}

  ngOnInit() {
    this.offerService.getOffers().subscribe(
      (response: Offer[]) => {
        response = response.filter(offer => offer.availabilities.some(available => available.promotion))
        response.forEach(offer => offer.availabilities.sort(function (x, y) {
          return (x.promotion === y.promotion) ? 0 : x ? -1 : 1;
        }))

        this.all = response
        this.offers = response

        this.setSelectedAvailable()
        this.setOfferPrice()
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      });
    this.offerService.getCountry().subscribe(
      (response: Country[]) => {
        this.countries = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      });

  }

  filter() {
    this.offers = this.all
    this.setSelectedAvailable()

    if (this.selectedCountries.length > 0) {
      this.filterCountries()
    }
    if (this.rangeDates != null && this.rangeDates.length > 0) {
      this.filterDate()
    }
  }

  filterCountries() {
    this.offers = this.offers.filter(offer => this.selectedCountries.some(country => country.name == offer.country.name))
  }

  filterDate() {
    if (this.rangeDates[1] != null) {
      this.offers.forEach(offer => {
          offer.selectedAvailabilities = offer.selectedAvailabilities.filter(available => {
            return new Date(available.datetimeStart).getTime() >= new Date(this.rangeDates[0]).getTime() &&
              new Date(available.datetimeEnd).getTime() <= new Date(this.rangeDates[1]).getTime()
          })
        }
      )
    } else if (this.rangeDates[0] != null) {
      this.offers.forEach(offer => {
          offer.selectedAvailabilities = offer.selectedAvailabilities.filter(available => {
            return new Date(available.datetimeStart).getTime() >= new Date(this.rangeDates[0]).getTime()
          })
        }
      )
    }

    this.offers = this.offers.filter(offer => offer.selectedAvailabilities.length > 0)

    this.setOfferPrice()
  }


  setOfferPrice() {
    this.offers.forEach(offer => {
      offer.days = ((new Date(offer.selectedAvailabilities[0].datetimeEnd).getTime() - new Date(offer.selectedAvailabilities[0].datetimeStart).getTime()) / this.millisecondsPerDay) + 1
      offer.price = (offer.hotel[0].rooms[0].price * offer.days) * this.people;
      if (offer.selectedAvailabilities[0].promotion != null && offer.selectedAvailabilities[0].promotion) {
        offer.promotionPrice = (offer.hotel[0].rooms[0].price - offer.selectedAvailabilities[0].promotionPrice) * offer.days * this.people;
      }
    })
  }

  clickMore() {
    this.number += 3;
  }

  private setSelectedAvailable() {
    this.offers.forEach(offer => {
      offer.selectedAvailabilities = offer.availabilities.filter(available => {
       return available.promotion
      })
    })
  }

  onSortChange(event: any) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    } else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }
}
