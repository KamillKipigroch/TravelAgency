import {Component, OnInit} from '@angular/core';
import {ConfirmationService} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {RoomDetail, RoomDetailService} from "../../../services/roomDetail.service";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './configRoomDetail.component.html',
  styleUrls: ['./configRoomDetail.component.css'],
  styles: [`
    :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
    }
  `],
  providers: [MessageService, ConfirmationService]
})
export class ConfigRoomDetailComponent implements OnInit {
  addEditDialog: boolean = false;

  all: RoomDetail[] = [];

  selected: RoomDetail[] = [];

  choose: RoomDetail;

  header: string = "Edit";

  submitted: boolean = false;

  constructor(private roomDetailService: RoomDetailService, private messageService: MessageService,
              private confirmationService: ConfirmationService) {
    this.choose = {
      id: 0,
      name: '',
      visible: true,
    };
  }


  ngOnInit(): void {
    this.choose = {
      id: 0,
      name: '',
      visible: true,
    };
    this.initOffers();
  }

  openNew() {
    this.submitted = false;
    this.addEditDialog = true;
    this.header = "Add new";
  }

  public initOffers(): void {
    this.roomDetailService.getAll().subscribe(
      (response: RoomDetail[]) => {
        this.all = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  deleteSelected() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.all = this.all?.filter(val => !this.selected?.includes(val));
        this.roomDetailService.disableVisibility(this.choose.id)
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000});
        this.selected = [];

      }
    });
  }

  edit(roomDetail: RoomDetail) {
    this.choose = {...roomDetail};
    this.addEditDialog = true;
    this.header = "Edit";

  }

  delete(room: RoomDetail) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to disable ' + room.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (room!.id != null && room.id != 0) {
          this.roomDetailService.disableVisibility(room.id).subscribe(
            (response) => {
              this.initOffers();
              this.messageService.add({
                severity: 'success',
                summary: 'Successful',
                detail: 'Offer Deleted',
                life: 3000
              });
            },
            (error) => {
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Something go wrong ' + error.detail,
                life: 3000
              });
            }
          )
        }
      }
    });
  }


  hideDialog() {
    this.addEditDialog = false;
    this.submitted = false;
    this.header = "Edit";
  }

  save() {
    this.submitted = true;

    if (this.choose.id != 0) {
      this.roomDetailService.update(this.choose).subscribe(
        (response) => {
          var index = this.all?.findIndex(val => val.id == response.id);
          this.all[index] = response;
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Updated', life: 3000});
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Something go wrong ' + error.message,
            life: 3000
          });
        }
      )
    } else {
      this.roomDetailService.add(this.choose!!).subscribe(
        (response: RoomDetail) => {
          this.all?.push(response);
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Created', life: 3000});
        },
        (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Something go wrong' + error.message,
            life: 3000
          });
        }
      )
    }
    this.all = [...this.all!];
    this.addEditDialog = false;
    this.header = "Edit";
    this.choose = {id: 0, name: '', visible: true};
  }
}
