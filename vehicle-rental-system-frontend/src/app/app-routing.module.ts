import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexPage } from './pages/index/index.page';
import { AddVehiclePage } from './pages/add-vehicle/add-vehicle.page';
import { BookingsPage } from './pages/bookings/bookings.page';
import { ContactPage } from './pages/contact/contact.page';
import { LoginPage } from './pages/login/login.page';
import { LogoutPage } from './pages/logout/logout.page';
import { ViewBookingsPage } from './pages/view-bookings/view-bookings.page';
import { SearchResultsPage } from './pages/search-results/search-results.page';

const routes: Routes = [
  { path: 'index', component: IndexPage },
  { path: 'add-vehicle', component: AddVehiclePage },
  { path: 'bookings', component: BookingsPage },
  { path: 'contact', component: ContactPage },
  { path: 'login', component: LoginPage },
  { path: 'logout', component: LogoutPage },
  { path: 'view-bookings', component: ViewBookingsPage },
  { path: 'results', component: SearchResultsPage},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: IndexPage }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
