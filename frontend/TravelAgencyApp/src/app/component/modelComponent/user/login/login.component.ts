import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { StorageService } from '../../../services/storage.service';
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
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
    const { username, password } = this.form;
    this.messageService.clear();
    this.authService.login(username, password).subscribe({
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
