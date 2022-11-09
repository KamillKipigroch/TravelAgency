import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {OfferService} from "./model/offer/offer.service";
import {HttpClientModule} from "@angular/common/http";
import {NavigationComponent} from './component/navigation/navigation.component';
import {RouterModule} from "@angular/router";
import {HomeComponent} from './component/home/home.component';
import {OfferComponent} from './component/offer/offer.component';
import {NotFoundComponent} from './component/not-found/not-found.component';
import {ConfigureOfferComponent} from './component/configure-offer/configure-offer.component';
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

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    OfferComponent,
    NotFoundComponent,
    ConfigureOfferComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'offers',
        component: OfferComponent
      },
      {
        path: 'offers/:businessKey',
        component: OfferComponent
      },
      {
        path: 'configure/offers',
        component: ConfigureOfferComponent
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
    InputTextModule
  ],
  providers: [OfferService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
