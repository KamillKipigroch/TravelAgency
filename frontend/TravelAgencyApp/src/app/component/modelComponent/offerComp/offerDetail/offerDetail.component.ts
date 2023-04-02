import {Component, OnInit, ViewChild} from '@angular/core';
import {Country, Offer, OfferAvailability, OfferService, Opinion, Room} from "../../../services/offer.service";
import {Router,} from "@angular/router";
import {StorageService} from "../../../services/storage.service";
import {MenuItem, MessageService} from "primeng/api";
import {OpinionRequest, OpinionService} from "../../../services/opinion.service";
import {formatDate} from "@angular/common";
import {OrderRequest, OrderService} from "../../../services/order.service";

@Component({
  selector: 'app-offer-component',
  templateUrl: './offerDetail.component.html',
  styleUrls: ['./offerDetail.component.css'],
  providers: [MessageService]
})
export class OfferDetailComponent implements OnInit {
  offer: Offer = new Offer(0, new Country(0, "", ""), [], [], [], [], "", true, 0, 0, 0);

  addEditOpinion: boolean = false;

  header: string = "Edit";

  newEditOpinion: OpinionRequest = new OpinionRequest();

  submitted: boolean = false;

  millisecondsPerDay = 1000 * 60 * 60 * 24;

  people: number = 1;

  items: MenuItem[] = [];

  detailTab = true;

  calendarTab = false;

  @ViewChild('myCalendar')
  private myCalendar: any;

  selectedMonth: number = 0

  roomTab = false;

  opinionsTab = false;

  roomSelected = 0;

  selectedDate: Date[] = [];

  allDates: any [] = [];

  selectedAvailability: OfferAvailability = new OfferAvailability();

  uploadedFiles: any[] = [];

  activeItem: MenuItem = {
    label: 'Detail', icon: 'pi pi-fw pi-home', command: () => {
      this.unselectAllTab();
      this.detailTab = true
    }
  };

  constructor(private router: Router, private offerService: OfferService, private orderService: OrderService, private messageService: MessageService,
              private storageService: StorageService, private opinionService: OpinionService) {
    if (this.router.getCurrentNavigation()!.extras!.state != null) {
      this.offer = this.router.getCurrentNavigation()!.extras!.state as Offer
      this.offer.hotel[0].rooms[0].selected = true
      this.initAllDates()
      this.initOrUpdateOfferData()
      this.detailTab = true
    }
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Detail', icon: 'pi pi-fw pi-home', command: () => {
          this.unselectAllTab();
          this.detailTab = true
        }
      },
      {
        label: 'Opinions', icon: 'pi pi-fw  pi-comments', command: () => {
          this.unselectAllTab();
          this.opinionsTab = true
        }
      },
      {
        label: 'Calendar', icon: 'pi pi-fw pi-calendar-times', command: () => {
          this.unselectAllTab();
          this.calendarTab = true
        }
      },
      {
        label: 'Room', icon: 'pi pi-fw pi-moon', command: () => {
          this.unselectAllTab();
          this.roomTab = true
        }
      }
    ];
    this.activeItem = this.items[0];

    const offerId = this.storageService.getOfferId()
    if (this.offer.id == 0) {
      this.offerService.getOfferById(offerId).subscribe(
        (response: Offer) => {
          this.offer = response as Offer
          this.setSelectedAvailable()
          this.initAllDates()
          this.initOrUpdateOfferData()
          this.offer.hotel[0].rooms[0].selected = true
          this.detailTab = true
        })
    }
  }

  private setSelectedAvailable() {
    this.offer!.selectedAvailabilities = this.offer!.availabilities
  }

  initAllDates() {
    this.offer!.availabilities.forEach(available => {
      this.allDates.push({
        label: 'Start date: ' + available.datetimeStart + ' date end: ' + available.datetimeEnd,
        value: available
      })
    })
    this.selectedAvailability = this.allDates[0].value;
  }

  initOrUpdateOfferData() {
    this.selectedDate[0] = new Date(this.selectedAvailability.datetimeStart)
    this.selectedDate[1] = new Date(this.selectedAvailability.datetimeEnd)

    this.offer!.days = ((new Date(this.selectedAvailability.datetimeEnd).getTime() -
      new Date(this.selectedAvailability.datetimeStart).getTime()) / this.millisecondsPerDay) + 1

    this.offer!.price = (this.offer!.hotel[0].rooms[this.roomSelected].price * this.offer!.days) * this.people;

    if (this.selectedAvailability.promotion != null && this.selectedAvailability.promotion) {

      this.offer!.promotionPrice =
        (this.offer!.hotel[this.roomSelected].rooms[this.roomSelected].price - this.selectedAvailability.promotionPrice) * this.offer!.days * this.people;
    }
    this.myCalendar?.updateModel(this.selectedDate!)
  }

  unselectAllTab() {
    this.opinionsTab = false;
    this.detailTab = false;
    this.calendarTab = false;
    this.roomTab = false;
  }

  selectedRoom(room: Room) {
    let index = this.offer.hotel[0].rooms.findIndex(r => room.id == r.id)
    this.unselectAllRooms()
    room.selected = true;
    this.roomSelected = index
    this.offer.hotel[0].rooms[index] = room;
    this.initOrUpdateOfferData()
  }

  unselectAllRooms() {
    this.offer.hotel[0].rooms.forEach(room => room.selected = false)
  }

  edit() {
    this.submitted = false;
    this.addEditOpinion = true;
    this.header = "Edit";
  }

  onUpload(event: any) {
    for (let file of event.files) {
      this.uploadedFiles.push(file)
    }
  }

  openNew() {
    if (this.storageService.isLoggedIn()) {
      this.submitted = false;
      this.addEditOpinion = true;
      this.header = "Add opinion for hotel: " + this.offer.hotel[0].name;
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'You must be logged to add opinion !',
        life: 3000
      });
    }
  }

  hideDialog() {
    this.addEditOpinion = false;
    this.submitted = false;
    this.header = "Edit";
  }

  save() {
    this.addEditOpinion = false;
    this.submitted = true;
    let user = this.storageService.getUser()
    this.newEditOpinion.userEmail = user.email;
    this.newEditOpinion.offerId = this.offer.id;

    this.opinionService.addOpinion(this.newEditOpinion!!).subscribe(
      (responseOpinion: Opinion) => {
        this.uploadedFiles.forEach(image => this.opinionService.uploadImage(responseOpinion.id, image).subscribe(
          (response: any) => {
            responseOpinion.opinionImages.push(response);
          })
        )
        this.uploadedFiles = [];
        this.offer.opinions?.push(responseOpinion);
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Opinion was added!', life: 3000});
      })

    this.newEditOpinion = new OpinionRequest();
    this.header = "Edit";
  }

  order() {
    if (this.storageService.isLoggedIn()) {
      let orderRequest = new OrderRequest();
      orderRequest.selectedRoom = this.offer!.hotel[0].rooms[this.roomSelected].id
      console.log(this.selectedAvailability.id)
      orderRequest.offerAvailabilityId = this.selectedAvailability.id;
      let user = this.storageService.getUser()
      orderRequest.userEmail = user.email;
      this.orderService.add(orderRequest).subscribe(
        () => {
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Ordered !', life: 3000});
        },
        (error: any) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: error.error.message,
            life: 3000
          });

        })
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'You must be logged to add opinion !',
        life: 3000
      });
    }
  }

  displayDate(opinion: Opinion): string {
    let date = new Date(opinion.createDate)
    return formatDate(date, 'short', 'en-US')
  }
}
