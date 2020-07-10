import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexPage } from './pages/index/index.page';
import { AddVehiclePage } from './pages/add-vehicle/add-vehicle.page';
import { BookingsPage } from './pages/bookings/bookings.page';
import { ContactPage } from './pages/contact/contact.page';
import { LoginPage } from './pages/login/login.page';
import { SignupPage } from './pages/signup/signup.page';
import { LogoutPage } from './pages/logout/logout.page';
import { ViewBookingsPage } from './pages/view-bookings/view-bookings.page';

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: '', component: IndexPage },
  { path: 'add-vehicle', component: AddVehiclePage },
  { path: 'bookings', component: BookingsPage },
  { path: 'contact', component: ContactPage },
  { path: 'login', component: LoginPage },
  { path: 'logout', component: LogoutPage },
  { path: 'signup', component: SignupPage },
  { path: 'view-bookings', component: ViewBookingsPage },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
