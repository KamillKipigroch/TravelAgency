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
  lastMinuteOffers: Offer[] = [];
  homeImages: any = [];
  millisecondsPerDay = 1000 * 60 * 60 * 24;
  isAdmin: boolean = false;

  constructor(private router: Router, private offerService: OfferService,
              private storageService: StorageService) {
  }

  ngOnInit(): void {

    const user: IUser = this.storageService.getUser();
    if (user.rol != null)
      this.isAdmin = user.rol.includes('Admin');

    this.offerService.getRecommendedOffers().subscribe(
      (response: Offer[]) => {
        this.offers = response
        this.setSelectedAvailable(this.offers)
        this.setOfferPrice(this.offers)
      })

    this.offerService.getLastMinuteOffers().subscribe(
      (response: Offer[]) => {
        this.lastMinuteOffers = response
        this.setSelectedAvailable(this.lastMinuteOffers)
        this.setOfferPrice(this.lastMinuteOffers)
      })
    this.homeImages.push("https://cdn.pixabay.com/photo/2017/01/20/00/30/maldives-1993704_960_720.jpg")
    this.homeImages.push("https://cdn.pixabay.com/photo/2021/01/09/21/05/victoria-falls-5903496_960_720.jpg")
    this.homeImages.push("https://cdn.pixabay.com/photo/2016/03/04/19/36/beach-1236581_960_720.jpg")
    this.homeImages.push("https://cdn.pixabay.com/photo/2014/11/21/03/26/neist-point-540119_960_720.jpg")
  }

  setOfferPrice(list :Offer[]) {
    list.forEach(offer => {
      offer.days = ((new Date(offer.selectedAvailabilities[0].datetimeEnd).getTime() - new Date(offer.selectedAvailabilities[0].datetimeStart).getTime()) / this.millisecondsPerDay) + 1
      offer.price = (offer.hotel[0].rooms[0].price * offer.days);
      if (offer.selectedAvailabilities[0].promotion != null && offer.selectedAvailabilities[0].promotion) {
        offer.promotionPrice = (offer.hotel[0].rooms[0].price - offer.selectedAvailabilities[0].promotionPrice) * offer.days;
      }
    })
  }

  private setSelectedAvailable(list :Offer[]) {
    list.forEach(offer => {
      offer.selectedAvailabilities = offer.availabilities
    })
  }

  checkOfferDetail(offer :Offer){
    this.storageService.saveOffer(offer.id.toString())
    this.router.navigate(['offer-detail'], {state: offer})
  }

  updateOfferDetail(offer :Offer){
    this.storageService.saveOffer(offer.id.toString())
    this.router.navigate(['configure/new-offer'], {state: offer})
  }
}
