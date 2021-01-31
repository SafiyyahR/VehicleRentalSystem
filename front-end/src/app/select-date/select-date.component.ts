import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from 'src/service/booking.service';
import { FormControl } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material';
import { Vehicle } from 'src/models/vehicle';
import { Observable } from 'rxjs';
import { $ } from 'protractor';

@Component({
  selector: 'app-select-date',
  templateUrl: './select-date.component.html',
  styleUrls: ['./select-date.component.css']
})
export class SelectDateComponent {
  newMinDropOffDate = new Date();
  newMaxDropOffDate = new Date();
  availableVehicles: Observable<Vehicle[]>;
  pickUpDate = new FormControl(new Date());
  dropOffDate = new FormControl(new Date());
  minPickUpDate = new Date();
  maxPickUpDate = new Date();
  minDropOffDate = new Date();
  maxDropOffDate = new Date();
  initDropOffDate = new Date();
  chosenDate = new Date();
  sPickUpDate: string;
  sDropOffDate: string;
  //the drop off and pick up dates are initialised 
  //the min pick up date is a day after the current date
  //min drop off date is one day after the pick up date chosen 
  //the maximumm pick up date is 6 months from the current date
  //the maximum drop of date is a month from the pchosen pick-up date
  constructor(private bookingService: BookingService, private router: Router) {
    this.pickUpDate = new FormControl(this.minPickUpDate);
    this.minPickUpDate.setDate(this.initDropOffDate.getDate() + 1)
    this.initDropOffDate.setDate(this.initDropOffDate.getDate() + 1);
    this.minDropOffDate.setDate(this.minPickUpDate.getDate() + 1);
    this.dropOffDate = new FormControl(this.minDropOffDate);
    this.maxPickUpDate.setMonth(this.minPickUpDate.getMonth() + 6);
    this.maxDropOffDate.setMonth(this.minPickUpDate.getMonth() + 1);
  }

  //if the user changes the dates then the minimum drop off date and the maximum drop off date is changed accordingly
  setDropOffDate($event: MatDatepickerInputEvent<Date>) {
    this.newMinDropOffDate = new Date($event.value);
    this.newMaxDropOffDate = new Date($event.value);
    this.minDropOffDate.setFullYear(this.newMinDropOffDate.getFullYear());
    this.minDropOffDate.setMonth(this.newMinDropOffDate.getMonth());
    this.minDropOffDate.setDate(this.newMinDropOffDate.getDate() + 1);
    this.maxDropOffDate.setFullYear(this.newMaxDropOffDate.getFullYear());
    this.maxDropOffDate.setMonth(this.newMaxDropOffDate.getMonth() + 1);
    this.maxDropOffDate.setDate(this.newMaxDropOffDate.getDate() + 1);
    this.dropOffDate = new FormControl(this.minDropOffDate);
   
  }
  //when the user submits the form all the available vehicles during the selected dates are retrieved
  //then the available vehicles are then sent as a parameter to update the list of available vehicles
  onSubmit() {
    this.availableVehicles = this.bookingService.findAllAvailableVehicles("available", this.pickUpDate.value, this.dropOffDate.value);
    this.availableVehicles.subscribe(
      data => {
        this.bookingService.updateAvailableVehicles(data);
      }, error => console.log(error));
      //once everything haas executed then the user is navigted to the results page
    this.router.navigate(['results']);
  }

}
