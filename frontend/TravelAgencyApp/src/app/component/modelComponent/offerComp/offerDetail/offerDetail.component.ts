import {Component, OnInit} from '@angular/core';
import {Country, Image, Offer, OfferService, Opinion, Room} from "../../../services/offer.service";
import {Router,} from "@angular/router";
import {IUser} from "../../../../model/user/user";
import {StorageService} from "../../../services/storage.service";
import {MenuItem, MessageService} from "primeng/api";
import {ImageRequest, OpinionRequest, OpinionService} from "../../../services/opinion.service";
import {OrderStatus} from "../../../services/oorderStatus.service";
import {formatDate} from "@angular/common";

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

  isAdmin: boolean = false;

  items: MenuItem[] = [];

  detailTab = true;

  calendarTab = false;

  roomTab = false;

  opinionsTab = false;

  roomSelected = 0;

  selectedDate: Date[] = [];

  allDates: any [] = [];

  selectedItem: any;

  uploadedFiles: any[] = [];

  responsiveOptions:any[] = [
    {
      breakpoint: '1024px',
      numVisible: 5
    },
    {
      breakpoint: '768px',
      numVisible: 3
    },
    {
      breakpoint: '560px',
      numVisible: 1
    }
  ];

  activeItem: MenuItem = {
    label: 'Detail', icon: 'pi pi-fw pi-home', command: (event: Event) => {
      this.unselectAllTab();
      this.detailTab = true
    }
  };

  constructor(private router: Router, private offerService: OfferService, private messageService: MessageService,
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
        label: 'Detail', icon: 'pi pi-fw pi-home', command: (event: Event) => {
          this.unselectAllTab();
          this.detailTab = true
        }
      },
      {
        label: 'Opinions', icon: 'pi pi-fw  pi-comments', command: (event: Event) => {
          this.unselectAllTab();
          this.opinionsTab = true
        }
      },
      {
        label: 'Calendar', icon: 'pi pi-fw pi-calendar-times', command: (event: Event) => {
          this.unselectAllTab();
          this.calendarTab = true
        }
      },
      {
        label: 'Room', icon: 'pi pi-fw pi-moon', command: (event: Event) => {
          this.unselectAllTab();
          this.roomTab = true
        }
      }
    ];
    this.activeItem = this.items[0];


    const user: IUser = this.storageService.getUser();
    if (user.rol != null)
      this.isAdmin = user.rol.includes('Admin');

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
      const days = ((new Date(available.datetimeEnd).getTime() -
        new Date(available.datetimeEnd).getTime()) / this.millisecondsPerDay) + 1

      const price = (this.offer!.hotel[0].rooms[this.roomSelected].price * days) * this.people;

      this.allDates.push({
        label: 'Start date: ' + available.datetimeStart + ' date end: ' + available.datetimeEnd + ' price: ' + price,
        value: available
      })
    })
    this.selectedItem = this.allDates[0].value;
  }

  initOrUpdateOfferData() {
    this.calendarTab = false
    this.selectedDate[0] = new Date(this.selectedItem.datetimeStart)
    this.selectedDate[1] = new Date(this.selectedItem.datetimeEnd)

    this.offer!.days = ((new Date(this.selectedItem.datetimeEnd).getTime() -
      new Date(this.selectedItem.datetimeStart).getTime()) / this.millisecondsPerDay) + 1

    this.offer!.price = (this.offer!.hotel[0].rooms[this.roomSelected].price * this.offer!.days) * this.people;

    if (this.selectedItem.promotion != null && this.selectedItem.promotion) {

      this.offer!.promotionPrice =
        (this.offer!.hotel[this.roomSelected].rooms[this.roomSelected].price - this.selectedItem.promotionPrice) * this.offer!.days * this.people;
    }

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

  edit(opinion: Opinion) {
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
    if(this.storageService.isLoggedIn()) {
      this.submitted = false;
      this.addEditOpinion = true;
      this.header = "Add opinion for hotel: " + this.offer.hotel[0].name;
    }
    else {
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
      })
    this.header = "Edit";
  }

  displayDate(opinion: Opinion):string {
    let date = new Date(opinion.createDate)
    return formatDate(date, 'short','en-US' )
  }
}
