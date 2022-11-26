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
import {ConfigureOfferComponent} from './component/modelComponent/offerComp/configOffer/configure-offer.component';
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {FileUploadModule} from "primeng/fileupload";
import {InputNumberModule} from "primeng/inputnumber";
import {FormsModule} from "@angular/forms";
import {RadioButtonModule} from "primeng/radiobutton";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {RatingModule} from "primeng/rating";
import {DialogModule} from "primeng/dialog";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RegisterComponent} from "./component/user/register/register.component";
import {LoginComponent} from "./component/user/login/login.component";
import {
  ConfigRoomDetailComponent
} from "./component/modelComponent/roomDetailComp/configRoomDetail/configRoomDetail.component";
import {CheckboxModule} from "primeng/checkbox";
import {
  ConfigOrderStatusComponent
} from "./component/modelComponent/order-status/configOrderStatus/configOrderStatus.component";

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    AppComponent,
    NavigationComponent,
    HomeComponent,
    OfferComponent,
    ConfigureOfferComponent,
    ConfigOrderStatusComponent,
    ConfigRoomDetailComponent,

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
        path: 'offers',
        component: OfferComponent
      },
      {
        path: 'configure/offers',
        component: ConfigureOfferComponent
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
    ]),
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
    CheckboxModule
  ],
  providers: [OfferService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
