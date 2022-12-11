import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {OfferService} from "./component/services/offer.service";
import {HttpClientModule} from "@angular/common/http";
import {NavigationComponent} from './component/navigation/navigation.component';
import {RouterModule} from "@angular/router";
import {HomeComponent} from './component/home/home.component';
import {OfferComponent} from './component/modelComponent/offerComp/offer/offer.component';
import {NotFoundComponent} from './component/not-found/not-found.component';
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {FileUploadModule} from "primeng/fileupload";
import {InputNumberModule} from "primeng/inputnumber";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RadioButtonModule} from "primeng/radiobutton";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {RatingModule} from "primeng/rating";
import {DialogModule} from "primeng/dialog";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RegisterComponent} from "./component/modelComponent/user/register/register.component";
import {LoginComponent} from "./component/modelComponent/user/login/login.component";
import {
  ConfigRoomDetailComponent
} from "./component/modelComponent/roomDetailComp/configRoomDetail/configRoomDetail.component";
import {CheckboxModule} from "primeng/checkbox";
import {
  ConfigOrderStatusComponent
} from "./component/modelComponent/order-status/configOrderStatus/configOrderStatus.component";
import {EmployeeComponent} from "./component/modelComponent/employee/employee/employee.component";
import {UserComponent} from "./component/modelComponent/user/user/user.component";
import {DataViewModule} from "primeng/dataview";
import {CardModule} from "primeng/card";
import {GalleriaModule} from "primeng/galleria";
import {AutoCompleteModule} from "primeng/autocomplete";
import {ListboxModule} from "primeng/listbox";
import {CalendarModule} from "primeng/calendar";
import {CaptchaModule} from "primeng/captcha";
import {OfferLastMinuteComponent} from "./component/modelComponent/offerComp/offerLastMinute/offerlm.component";
import {CarouselModule} from "primeng/carousel";
import {OfferDetailComponent} from "./component/modelComponent/offerComp/offerDetail/offerDetail.component";
import {GMapModule} from "primeng/gmap";
import {TabMenuModule} from "primeng/tabmenu";
import {DropdownModule} from "primeng/dropdown";
import {ToggleButtonModule} from "primeng/togglebutton";
import {InputTextareaModule} from "primeng/inputtextarea";
import {AccordionModule} from "primeng/accordion";

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    AppComponent,
    NavigationComponent,
    HomeComponent,
    OfferComponent,
    ConfigOrderStatusComponent,
    ConfigRoomDetailComponent,
    EmployeeComponent,
    UserComponent,
    OfferLastMinuteComponent,
    OfferDetailComponent,

    NotFoundComponent

  ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        HttpClientModule,
        RouterModule.forRoot([
                {
                    path: '',
                    redirectTo: 'home',
                    pathMatch: 'full'
                },
                {
                    path: 'home',
                    component: HomeComponent
                },
                {
                    path: 'all',
                    component: OfferComponent
                },
                {
                    path: 'offer-detail',
                    component: OfferDetailComponent
                },
                {
                    path: 'last-minute',
                    component: OfferLastMinuteComponent
                },
                {
                    path: 'employers',
                    component: EmployeeComponent
                },
                {
                    path: 'users',
                    component: UserComponent
                },
                {
                    path: 'configure/room-details',
                    component: ConfigRoomDetailComponent
                },
                {
                    path: 'configure/order-status',
                    component: ConfigOrderStatusComponent
                },
                {
                    path: 'login',
                    component: LoginComponent
                },
                {
                    path: 'register',
                    component: RegisterComponent
                },
                {
                    path: '**',
                    component: NotFoundComponent
                }
            ],
            {scrollPositionRestoration: 'enabled'}
        ),
        ButtonModule,
        TableModule,
        FileUploadModule,
        InputNumberModule,
        FormsModule,
        RadioButtonModule,
        ToastModule,
        ToolbarModule,
        ConfirmDialogModule,
        RatingModule,
        DialogModule,
        RippleModule,
        InputTextModule,
        CheckboxModule,
        DataViewModule,
        CardModule,
        GalleriaModule,
        AutoCompleteModule,
        ListboxModule,
        CalendarModule,
        CaptchaModule,
        ReactiveFormsModule,
        CarouselModule,
        GMapModule,
        TabMenuModule,
        DropdownModule,
        ToggleButtonModule,
        InputTextareaModule,
        AccordionModule,
    ],

  providers: [OfferService],
  bootstrap: [AppComponent],
})
export class AppModule {
}
