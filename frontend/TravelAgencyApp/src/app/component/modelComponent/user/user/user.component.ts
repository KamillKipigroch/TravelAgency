import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {User, UserService} from "../../../services/user.service";
import {IUser} from "../../../../model/user/user";
import {StorageService} from "../../../services/storage.service";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  styles: [`
    :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
    }
  `],
  providers: [MessageService, ConfirmationService]
})
export class UserComponent implements OnInit {
  addEditDialog: boolean = false;

  all: User[] = [];

  selected: User[] = [];

  choose: User;

  header: string = "Edit";

  submitted: boolean = false;

  showAdminBoard: boolean = false;

  showModeratorBoard: boolean = false;

  private roles: string[] = [];


  constructor(private userService: UserService, private messageService: MessageService,
              private confirmationService: ConfirmationService, private storageService: StorageService) {

    const user: IUser = this.storageService.getUser();
    this.roles = user.rol
    this.showAdminBoard = this.roles.includes('Admin');
    this.showModeratorBoard = this.roles.includes('Employee');

    this.choose = {
      id: 0,
      firstName: '',
      lastName: '',
      userRole: '',
      image: "",
      email: '',
      password: '',
      locked: false,
      enabled: false,
    };
  }


  ngOnInit(): void {
    this.clearChoose()
    this.initOffers();
  }

  openNew() {
    this.submitted = false;
    this.addEditDialog = true;
    this.header = "Add new";
  }

  public initOffers(): void {
    this.userService.getAllUsers().subscribe(
      (response: User[]) => {
        this.all = response;
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error', summary: 'Error', detail: 'Something go wrong ', life: 3000
        });
      }
    );
  }

  edit(roomDetail: User) {
    this.choose = {...roomDetail};
    this.addEditDialog = true;
    this.header = "Edit";

  }


  enable(user: User) {
      this.confirmationService.confirm({
        message: 'Are you sure you want to enable user with email ' + user.email + '?',
        header: 'Confirm',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          if (user!.email != '') {
            this.userService.enable(user).subscribe(
              (response) => {
                this.initOffers();
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
        }
      });

  }

  lockOrUnlock(user: User) {
    if(!user.locked) {
      this.confirmationService.confirm({
        message: 'Are you sure you want to lock user with email ' + user.email + '?',
        header: 'Confirm',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          if (user!.email != '') {
            this.userService.lock(user).subscribe(
              (response) => {
                this.initOffers();
                this.messageService.add({
                  severity: 'success',
                  summary: 'Successful',
                  detail: 'User locked',
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
    else {
      this.confirmationService.confirm({
        message: 'Are you sure you want to unlock user with email ' + user.email + '?',
        header: 'Confirm',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          if (user!.email != '') {
            this.userService.unlock(user).subscribe(
              (response) => {
                this.initOffers();
                this.messageService.add({
                  severity: 'success',
                  summary: 'Successful',
                  detail: 'User unlocked',
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
  }


  hideDialog() {
    this.addEditDialog = false;
    this.submitted = false;
    this.header = "Edit";
  }

  save() {
    this.submitted = true;

    if (this.choose.id != 0) {
      /*this.userService.update(this.choose).subscribe(
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
      )*/
    } else {
      this.choose.userRole = 'User'
      this.userService.add(this.choose!!).subscribe(
        (response: User) => {
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
    this.clearChoose()
  }

  private clearChoose(){
    this.choose = {
      id: 0,
      firstName: '',
      lastName: '',
      userRole: '',
      image: "",
      email: '',
      password: '',
      locked: false,
      enabled: false,
    };
  }
}
