import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [MessageService]
})
export class RegisterComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    email: null,
    password: null
  };
  isSuccessful = false;

  constructor(private messageService: MessageService,private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.messageService.clear();

    const {firstName, lastname, email, password} = this.form;

    this.authService.register(firstName, lastname, email, password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Your registration is successful!', life: 3000});
      },
      error: err => {
        this.messageService.add({severity: 'error', summary: 'Error', detail:  "Signup failed! "+err.error.message, life: 3000});
      }
    });
  }
}
