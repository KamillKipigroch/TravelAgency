import {Component, OnInit} from '@angular/core';
import {ConfirmationService} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {HttpErrorResponse} from "@angular/common/http";
import {IUser} from "../../../../model/user/user";
import {StorageService} from "../../../services/storage.service";
import {MailService, Question} from "../../../services/emailService.service";
import {User} from "../../../services/user.service";
import {logMessages} from "@angular-devkit/build-angular/src/builders/browser-esbuild/esbuild";

@Component({
  selector: 'app-configure-offer',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class QuestionComponent implements OnInit {
  addEditDialog: boolean = false;

  all: Question[] = [];

  selected: Question[] = [];

  choose: Question;

  submitted: boolean = false;

  showAdminBoard: boolean = false;

  showModeratorBoard: boolean = false;

  private roles: string[] = [];

  statuses: any;

  constructor(private questionService: MailService, private messageService: MessageService,
              private confirmationService: ConfirmationService, private storageService: StorageService) {
    this.choose = {
      id: 0,
      email: '',
      message: '',
      employee: new User(),
      answerMessage: '',
      answered: false
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

    this.choose = {
      id: 0,
      email: '',
      message: '',
      employee: new User(),
      answerMessage: '',
      answered: false
    };
    this.initQuestion();

  }

  openNew() {
    this.submitted = false;
    this.addEditDialog = true;
  }

  public initQuestion(): void {
    this.questionService.getAll().subscribe(
      (response: Question[]) => {
        this.all = response;
        if(this.showModeratorBoard){
          this.all = this.all.filter(question => !question.answered)
        }
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error', summary: 'Error', detail: 'Something go wrong ', life: 3000
        });
      }
    );
  }

  edit(question: Question) {
    this.choose = {...question};
    this.addEditDialog = true;
  }

  hideDialog() {
    this.addEditDialog = false;
    this.submitted = false;
  }

  save() {
    if (!this.choose.answered) {
      const user: IUser = this.storageService.getUser();
      this.choose.employee = new User();
      this.choose.employee.email = user.email
      this.choose.employee.userRole = user.rol[0]

      this.questionService.answerToQuestion(this.choose).subscribe(
        (response) => {
          let index = this.all?.findIndex(val => val.id == response.id);
          this.all[index] = response;
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Updated', life: 3000});
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Something go wrong ' + error.error.message,
            life: 3000
          });
        }
      )
      this.all = [...this.all!];
      this.choose = {
        id: 0,
        email: '',
        message: '',
        employee: new User(),
        answerMessage: '',
        answered: false
      };
    }
    this.addEditDialog = false;

  }
}
