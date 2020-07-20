import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialModule } from 'src/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IndexPage } from './pages/index/index.page';
import { FlexLayoutModule } from "@angular/flex-layout";
import { AddVehiclePage } from './pages/add-vehicle/add-vehicle.page';
import { BookingsPage } from './pages/bookings/bookings.page';
import { ContactPage } from './pages/contact/contact.page';
import { LoginPage } from './pages/login/login.page';
import { LogoutPage } from './pages/logout/logout.page';
import {ErrorPage} from './pages/error/error.page'
import { ViewBookingsPage } from './pages/view-bookings/view-bookings.page';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { BookingService } from 'src/services/booking.service';
import { VehicleService } from 'src/services/vehicle.service';
import { SearchResultsPage } from './pages/search-results/search-results.page';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    IndexPage,
    AddVehiclePage,
    BookingsPage,
    ContactPage,
    LoginPage,
    LogoutPage,
    ViewBookingsPage,
    SearchBarComponent,
    SearchResultsPage,
    ErrorPage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule
  ],
  providers: [BookingService, VehicleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
