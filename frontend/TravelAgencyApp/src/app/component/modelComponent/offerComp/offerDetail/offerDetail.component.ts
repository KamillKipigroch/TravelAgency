import {Component, OnInit} from '@angular/core';
import {Offer, OfferService} from "../../../services/offer.service";
import {Router,} from "@angular/router";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-offer-component',
  templateUrl: './offerDetail.component.html',
  styleUrls: ['./offerDetail.component.css']
})
export class OfferDetailComponent implements OnInit {
  offer: Offer ;

  millisecondsPerDay = 1000 * 60 * 60 * 24;

  people: number = 1;


  options: any;

  overlays: any[];

  dialogVisible: boolean;

  markerTitle: string;

  selectedPosition: any;

  infoWindow: any;

  draggable: boolean;

  constructor(private messageService: MessageService,private router: Router, private offerService: OfferService) {
    if (this.router.getCurrentNavigation()!.extras!.state != null) {
      this.offer = this.router.getCurrentNavigation()!.extras!.state as Offer
      this.setOfferPrice()
    }
    else {
      this.offer = new Offer();
    }
  }

  ngOnInit() {
    this.options = {
      center: {lat: 36.890257, lng: 30.707417},
      zoom: 12
    };

    this.initOverlays();

    this.infoWindow = new google.maps.InfoWindow();
  }
  setOfferPrice() {
    console.log(this.offer)
    console.log(this.offer.selectedAvailabilities)
    this.offer.days = ((new Date(this.offer.selectedAvailabilities[0].datetimeEnd).getTime() - new Date(this.offer.selectedAvailabilities[0].datetimeStart).getTime()) / this.millisecondsPerDay) + 1
    this.offer.price = (this.offer.hotel[0].rooms[0].price * this.offer.days) * this.people;
    if (this.offer.selectedAvailabilities[0].promotion != null && this.offer.selectedAvailabilities[0].promotion) {
      this.offer.promotionPrice = (this.offer.hotel[0].rooms[0].price - this.offer.selectedAvailabilities[0].promotionPrice) * this.offer.days * this.people;
    }
  }

  handleMapClick(event) {
    this.dialogVisible = true;
    this.selectedPosition = event.latLng;
  }

  handleOverlayClick(event) {
    let isMarker = event.overlay.getTitle != undefined;

    if (isMarker) {
      let title = event.overlay.getTitle();
      this.infoWindow.setContent('' + title + '');
      this.infoWindow.open(event.map, event.overlay);
      event.map.setCenter(event.overlay.getPosition());

      this.messageService.add({severity:'info', summary:'Marker Selected', detail: title});
    }
    else {
      this.messageService.add({severity:'info', summary:'Shape Selected', detail: ''});
    }
  }

  addMarker() {
    this.overlays.push(new google.maps.Marker({position:{lat: this.selectedPosition.lat(), lng: this.selectedPosition.lng()}, title:this.markerTitle, draggable: this.draggable}));
    this.markerTitle = null;
    this.dialogVisible = false;
  }

  handleDragEnd(event) {
    this.messageService.add({severity:'info', summary:'Marker Dragged', detail: event.overlay.getTitle()});
  }

  initOverlays() {
    if (!this.overlays||!this.overlays.length) {
      this.overlays = [
        new google.maps.Marker({position: {lat: 36.879466, lng: 30.667648}, title:"Konyaalti"}),
        new google.maps.Marker({position: {lat: 36.883707, lng: 30.689216}, title:"Ataturk Park"}),
        new google.maps.Marker({position: {lat: 36.885233, lng: 30.702323}, title:"Oldtown"}),
        new google.maps.Polygon({paths: [
            {lat: 36.9177, lng: 30.7854},{lat: 36.8851, lng: 30.7802},{lat: 36.8829, lng: 30.8111},{lat: 36.9177, lng: 30.8159}
          ], strokeOpacity: 0.5, strokeWeight: 1,fillColor: '#1976D2', fillOpacity: 0.35
        }),
        new google.maps.Circle({center: {lat: 36.90707, lng: 30.56533}, fillColor: '#1976D2', fillOpacity: 0.35, strokeWeight: 1, radius: 1500}),
        new google.maps.Polyline({path: [{lat: 36.86149, lng: 30.63743},{lat: 36.86341, lng: 30.72463}], geodesic: true, strokeColor: '#FF0000', strokeOpacity: 0.5, strokeWeight: 2})
      ];
    }
  }

  zoomIn(map) {
    map.setZoom(map.getZoom()+1);
  }

  zoomOut(map) {
    map.setZoom(map.getZoom()-1);
  }

  clear() {
    this.overlays = [];
  }
}
