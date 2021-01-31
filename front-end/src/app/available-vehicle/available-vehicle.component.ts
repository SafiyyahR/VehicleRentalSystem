import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/models/vehicle';
import { Observable, BehaviorSubject } from 'rxjs';
import { VehicleService } from 'src/service/vehicle.service';
import { Router } from '@angular/router';
import { BookingService } from 'src/service/booking.service';
import { Schedule } from 'src/models/schedule';

@Component({
  selector: 'app-available-vehicle',
  templateUrl: './available-vehicle.component.html',
  styleUrls: ['./available-vehicle.component.css']
})
export class AvailableVehicleComponent implements OnInit {
  //declare array of Vehicle
  vehicles: Vehicle[];
  constructor(private bookingService: BookingService, private router: Router) {
    //calls the uploads function from the booking Service
    this.uploadVehicleList();
  }

  ngOnInit() {
  }

  //this method is used to assign the available vehicles to the array declared above
  uploadVehicleList() {

    this.bookingService.currentAvailableVehicles.subscribe(vehicles => this.vehicles = vehicles);
  }

  //the method is used to navigate to the page used to reserve the vehicle
  reserve(vehicle: Vehicle) {
    //this function is used to update the vehicle to be reserved
    this.bookingService.updateChosenVehicle(vehicle);
    //used to navigate
    this.router.navigate(['bookings/add']);
  }

  //the method called when the go back button is clicked when there is no available vehicles for the chosen dates
  goBack() {
    this.router.navigate(['reserve']);
  }



}
