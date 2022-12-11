import {Component, OnInit} from '@angular/core';
import {ConfirmationService} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {OrderStatus, OrderStatusService} from "../../../services/oorderStatus.service";
import {IUser} from "../../../../model/user/user";
import {StorageService} from "../../../services/storage.service";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './configOrderStatus.component.html',
  styleUrls: ['./configOrderStatus.component.css'],
  styles: [`
    :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
    }
  `],
  providers: [MessageService, ConfirmationService]
})
export class ConfigOrderStatusComponent implements OnInit {
  addEditDialog: boolean = false;

  all: OrderStatus[] = [];

  selected: OrderStatus[] = [];

  choose: OrderStatus;

  header: string = "Edit";

  submitted: boolean = false;

  showAdminBoard: boolean = false;

  showModeratorBoard: boolean = false;

  private roles: string[] = [];

  constructor(private orderStatusService: OrderStatusService, private messageService: MessageService,
              private confirmationService: ConfirmationService, private storageService: StorageService) {
    this.choose = {
      id: 0,
      name: '',
      visible: true,
    };
  }


  ngOnInit(): void {
    const user: IUser = this.storageService.getUser();
    this.roles = user.rol
    this.showAdminBoard = this.roles.includes('Admin');
    this.showModeratorBoard = this.roles.includes('Employee');

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
    this.orderStatusService.getAll().subscribe(
      (response: OrderStatus[]) => {
        this.all = response;
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error', summary: 'Error', detail: 'Something go wrong ', life: 3000
        });
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
        this.orderStatusService.disableVisibility(this.choose.id)
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000});
        this.selected = [];

      }
    });
  }

  edit(roomDetail: OrderStatus) {
    this.choose = {...roomDetail};
    this.addEditDialog = true;
    this.header = "Edit";
  }

  delete(room: OrderStatus) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to disable ' + room.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (room!.id != null && room.id != 0) {
          this.orderStatusService.disableVisibility(room.id).subscribe(
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
      this.orderStatusService.update(this.choose).subscribe(
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
      this.orderStatusService.add(this.choose!!).subscribe(
        (response: OrderStatus) => {
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
