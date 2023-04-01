import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {MailService, Question} from "./component/services/emailService.service";
import {User} from "./component/services/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})
export class AppComponent implements OnInit {

  form: any = {
    addressEmail: null,
    message: null
  };

  constructor(private messageService: MessageService, private mailService: MailService) {
  }

  ngOnInit() {
  }

  onSubmit(): void {
    const {addressEmail, message} = this.form;

    let mail = new Question();
    mail.email = addressEmail;
    mail.message = message;
    let user = new User();
    user.userRole = 'Employee'
    mail.employee = user;

    this.messageService.clear();

    this.mailService.addQuestion(mail).subscribe({
      next: data => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: "We will respond as soon as possible",
          life: 3000
        });
      },
      error: err => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: "Error! " + err.error.message,
          life: 3000
        });
      },
    });
  }
}
