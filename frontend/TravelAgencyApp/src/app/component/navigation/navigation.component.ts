import {Component, OnInit} from '@angular/core';
import {StorageService} from '../services/storage.service';
import {MessageService} from "primeng/api";
import {IUser} from "../../model/user/user";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
  providers: [MessageService]
})
export class NavigationComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  constructor(
    private storageService: StorageService,
    private messageService:MessageService
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user:IUser = this.storageService.getUser();
      this.roles = user.rol
      this.showAdminBoard = this.roles.includes('Admin');
      this.showModeratorBoard = this.roles.includes('Employee');

      this.username = user.name;
    }
  }

  resetItem(){
    this.storageService.resetItem()
  }

  logout(): void {
    this.messageService.add({severity: 'info', summary: 'You can login again', detail:  "You can login again", life: 3000});

    this.storageService.logout();
  }

}
