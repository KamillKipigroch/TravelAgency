import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {IUser} from "../../../../model/user/user";
import {StorageService} from "../../../services/storage.service";
import {User} from "../../../services/user.service";
import {Order, OrderService} from "../../../services/order.service";
import {OrderStatus} from "../../../services/orderStatus.service";
import {OfferAvailability, Room} from "../../../services/offer.service";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class OrderComponent implements OnInit {
  addEditDialog: boolean = false;

  all: Order[] = [];

  selected: Order[] = [];

  choose: Order;

  submitted: boolean = false;

  showAdminBoard: boolean = false;

  showModeratorBoard: boolean = false;

  email :String = ''

  private roles: string[] = [];

  statuses: any;

  constructor(private orderService: OrderService, private messageService: MessageService,
              private confirmationService: ConfirmationService, private storageService: StorageService) {
    this.choose = {
      id: 0,
      orderStatus: new OrderStatus(),
      deadline: new OfferAvailability(),
      room: new Room(),
      user: new User(),
      price: 0
    };
  }


  ngOnInit(): void {
    this.statuses = [
      {label: 'INSTOCK', value: 'instock'},
      {label: 'LOWSTOCK', value: 'lowstock'},
      {label: 'OUTOFSTOCK', value: 'outofstock'}
    ];


    const user: IUser = this.storageService.getUser();
    this.roles = user.rol
    this.showAdminBoard = this.roles.includes('Admin');
    this.showModeratorBoard = this.roles.includes('Employee');
    this.email = user.email
    this.choose = {
      id: 0,
      orderStatus: new OrderStatus(),
      deadline: new OfferAvailability(),
      room: new Room(),
      user: new User(),
      price: 0
    };
    this.initOrders();
  }

  openNew() {
    this.submitted = false;
    this.addEditDialog = true;
  }

  public initOrders(): void {
    if (this.showModeratorBoard || this.showAdminBoard) {
      this.orderService.getAll().subscribe(
        (response: Order[]) => {
          console.log(response)
          this.all = response;
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({
            severity: 'error', summary: 'Error', detail: 'Something go wrong ', life: 3000
          });
        }
      );
    }
    else {
      this.orderService.getCustomer(this.email).subscribe(
        (response: Order[]) => {
          console.log(response)
          this.all = response;
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({
            severity: 'error', summary: 'Error', detail: 'Something go wrong ', life: 3000
          });
        }
      );
    }

  }


  edit(order: Order) {
    this.choose = {...order};
    this.addEditDialog = true;
  }

  hideDialog() {
    this.addEditDialog = false;
    this.submitted = false;
  }

  changeToNextStatus(order: Order) {
    let msg = '';
    if (order.orderStatus.level == 0) {
      msg = ' You must sura its paid ! '
    }
    this.confirmationService.confirm({
      message: 'Are you sure you want change this order status to next ? ' + msg,
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.orderService.nextStatus(order).subscribe(
          (response) => {
            this.initOrders();
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'User enabled',
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
    })
  }


  cancel(order: Order) {
    this.confirmationService.confirm({
      message: 'Are you sure you want change to cancel this order?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.orderService.cancel(order).subscribe(
          (response) => {
            this.initOrders();
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Order Canceled',
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
    })
  }

}
