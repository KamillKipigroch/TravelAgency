import {Component, OnInit} from '@angular/core';
import {Country, Hotel, Offer, OfferAvailability, OfferService, Room} from "../../../services/offer.service";
import {Router,} from "@angular/router";
import {StorageService} from "../../../services/storage.service";
import {MessageService} from "primeng/api";
import {OrderService} from "../../../services/order.service";
import {HttpErrorResponse} from "@angular/common/http";
import {RoomDetail, RoomDetailService} from "../../../services/roomDetail.service";

@Component({
  selector: 'app-offer-component',
  templateUrl: './newEditOffer.component.html',
  styleUrls: ['./newEditOffer.component.css'],
  providers: [MessageService]
})
export class NewEditOfferComponent implements OnInit {
  offer: Offer = new Offer(0, new Country(0, "", ""), [], [], [], [], "", true, 0, 0, 0);
  detailTab = true;
  calendarTab = false;
  roomTab = false;
  roomDetails: RoomDetail[] = [];
  offerImage: File[] = [];
  roomImage: File[] = [];
  countries: Country[] = [];
  console = console;
  todayDate = new Date();
  rangeDates = [];

  tabs = [
    {
      label: 'Detail', icon: 'pi pi-fw pi-home', command: () => {
        this.unselectAllTabs();
        this.detailTab = true
      }
    },
    {
      label: 'Calendar', icon: 'pi pi-fw pi-calendar-times', command: () => {
        this.unselectAllTabs();
        this.calendarTab = true
      }
    },
    {
      label: 'Room', icon: 'pi pi-fw pi-moon', command: () => {
        this.unselectAllTabs();
        this.roomTab = true
      }
    }
  ];
  activeTab = this.tabs[0]

  constructor(private router: Router, private offerService: OfferService, private orderService: OrderService, private messageService: MessageService,
              private storageService: StorageService, private roomDetailService: RoomDetailService) {
    if (this.router.getCurrentNavigation()!.extras!.state != null) {
      this.offer = this.router.getCurrentNavigation()!.extras!.state as Offer
      this.offer.hotel[0].rooms[0].selected = true
    }
    if (this.offer.hotel.length == 0) {
      this.offer.hotel.push(new Hotel(0, "", 0, [], 0, 0, true))
    }
  }

  ngOnInit() {
    this.roomDetailService.getAll().subscribe(
      (response: RoomDetail[]) => {
        this.roomDetails = response;
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error', summary: 'Error', detail: 'Something go wrong ' + error.error.message(), life: 3000
        });
      }
    );

    this.offerService.getCountry().subscribe(
      (response: Country[]) => {
        this.countries = response;
      },
      (error: HttpErrorResponse) => {
        this.messageService.clear();
        this.messageService.add({
          severity: 'error', summary: 'Error',
          detail: 'Something go wrong ' + error.error.message, life: 3000
        });
      });


    const offerId = this.storageService.getOfferId()
    if (false) {
      this.offerService.getOfferById(offerId).subscribe(
        (response: Offer) => {
          this.offer = response as Offer
        })
    }
    this.offer.availabilities.push(new OfferAvailability())
  }

  addNewRoom() {
    this.offer.hotel[0].rooms.push(new Room());
  }

  unselectAllTabs() {
    this.detailTab = false;
    this.calendarTab = false;
    this.roomTab = false;
  }

  onUploadOfferImage(event: any) {
    for (let file of event.files) {
      this.offerImage.push(file)
    }
  }

  onUploadRoomImage(event: any) {
    for (let file of event.files) {
      this.roomImage.push(file)
    }
  }

  deleteLastRoom() {
    this.offer.hotel[0].rooms.splice(-1)
  }

  addEditOffer() {
    console.log(this.offer)
    if (this.validData()) {

      this.offerService.addOffer(this.offer).subscribe(
        (response) => {
          this.messageService.add({severity: 'info', summary: 'Successful', detail: "s", life: 3000});
        },
        (error:any) =>{
          this.messageService.add({
            severity: 'error', summary: 'Error',
            detail: 'Something go wrong with '+error.error.message, life: 3000
          });}
      )
    }

  }

  checkString(text: String): boolean {
    return "" != text;
  }

  checkArray(array: any[]): boolean {
    return array.length != 0;
  }

  checkNumber(num: number): boolean {
    return num != 0;
  }

  private validData(): boolean {
    let valid = this.checkArray(this.offerImage)
    this.showInfoIfNoValid("offer images", valid)

    if (valid) {
      valid = this.checkString(this.offer.description)
      this.showInfoIfNoValid("offer description", valid)
    }

    if (valid) {
      valid = this.checkNumber(this.offer.country.id)
      this.showInfoIfNoValid("offer country", valid)
    }
    if (valid) {
      valid = this.checkString(this.offer.description)
      this.showInfoIfNoValid("offer description", valid)
    }
    if (valid) {
      valid = this.checkNumber(this.offer.hotel[0].standard)
      this.showInfoIfNoValid("offer standard", valid)
    }
    if (valid) {
      valid = this.checkArray(this.offer.hotel[0].rooms)
      this.showInfoIfNoValid("rooms", valid)
    }

    if (valid) {
      valid = this.checkArray(this.roomImage)
      this.showInfoIfNoValid("roomImage", valid)
    }

    if (valid) {
      for (let i = 0, len = this.offer.hotel[0].rooms.length; i < len; i++) {
        let room = this.offer.hotel[0].rooms[i];
        valid = this.checkString(room.description)
        if(valid){
          valid = this.checkNumber(room.quantity)
          this.showInfoIfNoValid("room quantity", valid)
        }
        if(valid){
          valid = this.checkString(room.description)
          this.showInfoIfNoValid("room description", valid)
        }
        if(valid){
          valid = this.checkNumber(room.roomDetail.id)
          this.showInfoIfNoValid("room roomDetail", valid)
        }
        if (!valid) {
          break
        }
      }
    }

    return valid
  }

  showInfoIfNoValid(text: String, valid:boolean){
    if(!valid){
      this.messageService.add({
        severity: 'error', summary: 'Error',
        detail: 'Something go wrong with '+text, life: 3000
      });
    }
  }
}
