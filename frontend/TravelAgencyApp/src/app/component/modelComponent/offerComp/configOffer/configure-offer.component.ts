import {Component, OnInit} from '@angular/core';
import {Offer} from "../../../../model/offer/offer";
import {ConfirmationService} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {OfferService} from "../../../services/offer.service";
import {empty} from "rxjs";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './configure-offer.component.html',
  styleUrls: ['./configure-offer.component.css'],
  styles: [`
    :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
    }
  `],
  providers: [MessageService, ConfirmationService]
})
export class ConfigureOfferComponent implements OnInit {
  offerDialog: boolean = false;

  offers: Offer[] = [] ;

  selectedOffers:  Offer[] = [];

  offer: Offer;

  submitted: boolean = false;

  statuses: any[];

  constructor(private offerService: OfferService, private messageService: MessageService, private confirmationService: ConfirmationService) {

    this.offer = {
      businessKey: '',
      price: 0.0,
      country: "",
      imageUrl: "",
      createDate: new Date(Date.now()),
    };

    this.statuses = [
      {label: 'INSTOCK', value: 'instock'},
      {label: 'LOWSTOCK', value: 'lowstock'},
      {label: 'OUTOFSTOCK', value: 'outofstock'}
    ];
  }


  ngOnInit(): void {
    this.statuses = [
      {label: 'INSTOCK', value: 'instock'},
      {label: 'LOWSTOCK', value: 'lowstock'},
      {label: 'OUTOFSTOCK', value: 'outofstock'}
    ];
    this.offer = {
      businessKey: '',
      price: 0.0,
      country: "",
      imageUrl: "",
      createDate: new Date(Date.now()),
    };
    this.initOffers();
  }

  openNew() {
    this.submitted = false;
    this.offerDialog = true;
  }

  public initOffers(): void {
    this.offerService.getOffers().subscribe(
      (response: Offer[]) => {
        this.offers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public pushNewOffer(): void {
    this.offerService.getOffers().subscribe(
      (response: Offer[]) => {
        this.offers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  deleteSelectedOffers() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.offers = this.offers?.filter(val => !this.selectedOffers?.includes(val));
        this.selectedOffers = [];
        this.offerService.deleteOffer(this.offer.businessKey)
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Products Deleted', life: 3000});
      }
    });
  }

  editOffer(offer: Offer) {
    this.offer = {...offer};
    this.offerDialog = true;
  }

  deleteOffer(offer: Offer) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + offer.businessKey + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.offers = this.offers?.filter(val => val.businessKey !== offer.businessKey);
        if(offer!.businessKey !=null)
          this.offerService.deleteOffer(offer.businessKey);
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Offer Deleted', life: 3000});
      }
    });
  }
  findIndexByBusinessKey(businessKey: string): Offer|undefined {
    return this.offers?.find(off => off.businessKey == businessKey);
  }


  hideDialog() {
    this.offerDialog = false;
    this.submitted = false;
  }

  saveOffer() {
    this.submitted = true;

    if (!this.offer?.country.trim()) {
      return;
    }
    if (this.offer.businessKey) {
      this.offerService.updateOffer(this.offer)
      this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
    } else {
      this.offerService?.addOffer(this.offer!!);
      this.offers?.push();
      this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000});
    }
    this.offers = [...this.offers!];
    this.offerDialog = false;
    this.offer = {businessKey: "", country: "", createDate: new Date(Date.now()), imageUrl: "", price: 0};
  }
}
