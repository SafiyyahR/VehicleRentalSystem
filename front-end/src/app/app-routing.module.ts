import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { ReserveVehicleComponent } from './reserve-vehicle/reserve-vehicle.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { AvailableVehicleComponent } from './available-vehicle/available-vehicle.component';
import { SelectDateComponent } from './select-date/select-date.component';
import { LoginComponent } from './login/login.component';

//routes in the app
const routes: Routes = [
  { path: '', redirectTo: 'booking', pathMatch: 'full' },
  { path: 'vehicles', component: VehicleListComponent },
  { path: 'bookings/add', component: ReserveVehicleComponent },
  { path: 'bookings/update/:id', component: UpdateBookingComponent },
  { path: 'bookings/:id', component: ViewBookingComponent },
  { path: 'bookings', component: BookingListComponent },
  { path: 'results', component: AvailableVehicleComponent },
  { path: 'reserve', component: SelectDateComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
