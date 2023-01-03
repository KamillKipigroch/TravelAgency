import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { StorageService } from '../../../services/storage.service';
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-login',
  templateUrl: './changePassword.component.html',
  styleUrls: ['./changePassword.component.css'],
  providers: [MessageService]
})
export class ChangePasswordComponent implements OnInit {
  form: any = {
    lastPassword: null,
    newPassword: null
  };
  isLoggedIn = false;
  roles: string[] = [];

  constructor(private messageService: MessageService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.roles = this.storageService.getUser().roles;
    }
  }

  onSubmit(): void {
    const { lastPassword, newPassword } = this.form;
    let userEmail = this.storageService.getEmail()
    this.messageService.clear();
    this.authService.changePassword(userEmail, lastPassword, newPassword).subscribe({
      next: data => {
        this.reloadPage();
        this.isLoggedIn = true;
        this.storageService.saveToken(data.accessToken);
      },
      error: err => {
        this.isLoggedIn = false;
        this.messageService.add({severity: 'error', summary: 'Error', detail:  "Login failed! "+err.error.message, life: 3000});
      },
    });
  }

  reloadPage(): void {
    window.location.replace("http://localhost:4200/home");
  }
}
