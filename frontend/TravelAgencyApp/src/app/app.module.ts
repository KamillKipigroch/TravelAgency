import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {OfferService} from "./model/offer/offer.service";
import {HttpClientModule} from "@angular/common/http";
import {NavigationComponent} from './component/navigation/navigation.component';
import {RouterModule} from "@angular/router";
import {HomeComponent} from './component/home/home.component';
import {OfferComponent} from './component/offer/offer.component';
import { NotFoundComponent } from './component/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    OfferComponent,
    NotFoundComponent
  ],
  imports: [
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
        path: '**',
        component: NotFoundComponent
      }
    ])
  ],
  providers: [OfferService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
