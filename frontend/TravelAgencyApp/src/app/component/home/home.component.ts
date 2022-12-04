import {Component, OnInit} from '@angular/core';
import {Offer, OfferService} from "../services/offer.service";
import {StorageService} from "../services/storage.service";
import {IUser} from "../../model/user/user";
import {Router, NavigationExtras} from "@angular/router";


@Component({
  selector: 'app-home-component',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  offers: Offer[] = [];

  millisecondsPerDay = 1000 * 60 * 60 * 24;
  isAdmin: boolean = false;

  constructor(private router: Router, private offerService: OfferService,
              private storageService: StorageService) {
  }

  ngOnInit(): void {
    const user: IUser = this.storageService.getUser();
    if (user.rol != null)
      this.isAdmin = user.rol.includes('Admin');
    this.offerService.getOffers().subscribe(
      (response: Offer[]) => {
        this.offers = response
        this.setSelectedAvailable()
        this.setOfferPrice()
      })
  }

  setOfferPrice() {
    this.offers.forEach(offer => {
      offer.days = ((new Date(offer.selectedAvailabilities[0].datetimeEnd).getTime() - new Date(offer.selectedAvailabilities[0].datetimeStart).getTime()) / this.millisecondsPerDay) + 1
      offer.price = (offer.hotel[0].rooms[0].price * offer.days);
      if (offer.selectedAvailabilities[0].promotion != null && offer.selectedAvailabilities[0].promotion) {
        offer.promotionPrice = (offer.hotel[0].rooms[0].price - offer.selectedAvailabilities[0].promotionPrice) * offer.days;
      }
    })
  }

  private setSelectedAvailable() {
    this.offers.forEach(offer => {
      offer.selectedAvailabilities = offer.availabilities
    })
  }

  checkOfferDetail(offer :Offer){
    this.router.navigate(['offer-detail'], {state: offer})
  }

}
