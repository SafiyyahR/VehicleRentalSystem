import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { MaterialModule } from '../material/material.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { SelectDateComponent } from './select-date/select-date.component';
import { AvailableVehicleComponent } from './available-vehicle/available-vehicle.component';
import { ReserveVehicleComponent } from './reserve-vehicle/reserve-vehicle.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BookingListComponent } from './booking-list/booking-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewVehicleComponent } from './view-vehicle/view-vehicle.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookingService } from 'src/service/booking.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    SelectDateComponent,
    AvailableVehicleComponent,
    ReserveVehicleComponent,
    ViewBookingComponent,
    UpdateBookingComponent,
    BookingListComponent,
    ViewVehicleComponent,
    LoginComponent,
  ],
  entryComponents: [ViewVehicleComponent, ViewBookingComponent,UpdateBookingComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [BookingService],
  bootstrap: [AppComponent],
  schemas: [
    NO_ERRORS_SCHEMA,
  ]
})
export class AppModule { }
