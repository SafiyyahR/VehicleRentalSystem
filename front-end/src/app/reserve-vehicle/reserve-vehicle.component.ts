import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/models/booking';
import { BookingService } from 'src/service/booking.service';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { Schedule } from 'src/models/schedule';
import { Vehicle } from 'src/models/vehicle';

@Component({
  selector: 'app-reserve-vehicle',
  templateUrl: './reserve-vehicle.component.html',
  styleUrls: ['./reserve-vehicle.component.css']
})
export class ReserveVehicleComponent implements OnInit {
  schedule: Schedule;
  vehicle: Vehicle;
  booking: Booking = new Booking();
  minDateOfBirth = new Date();
  maxDateOfBirth = new Date();
  initDate = new Date();
  dob = new Date();
  constructor(private bookingService: BookingService, private route: Router) {
  }

  ngOnInit() {
    //the minimum age to book and drive the reserved vehicle is 25 and the maximum is 69
    this.minDateOfBirth.setFullYear(this.initDate.getFullYear() - 25);
    this.maxDateOfBirth.setFullYear(this.initDate.getFullYear() - 69);
    //the dob key of the formGroup is assigned a form control with the required validator and the maximum data of birth as the initial one
    this.bookingService.formGroup.controls["dob"] = new FormControl(this.maxDateOfBirth, [Validators.required]);
    //also subscribes to lates value in the schedule and the vehicle chosen to be reserved 
    this.bookingService.currentSchedule.subscribe(schedule => this.schedule = schedule);
    this.bookingService.currentChosenVehicle.subscribe(vehicle => this.vehicle = vehicle);
  }

  //the methods that will be called if the user enters anything incorrect
  getEmailErrMes() {
    return this.bookingService.formGroup.controls["email"].hasError('required') ? 'You must enter a value' :
      this.bookingService.formGroup.controls["email"].hasError('email') ? 'Not a valid email' :
        '';
  }

  getTelErrMes() {
    return this.bookingService.formGroup.controls["telphoneNo"].hasError('required') ? 'You must enter a value' :
      this.bookingService.formGroup.controls["telphoneNo"].hasError('pattern') ? 'Not a valid UK based Telephone Number' :
        '';
  }
  getDobErrMes() {
    return this.bookingService.formGroup.controls["dob"].hasError('required') ? 'You must enter a value' : '';
  }
  getLNameErrMes() {
    return this.bookingService.formGroup.controls["lname"].hasError('required') ? 'You must enter a value' :
      this.bookingService.formGroup.controls["lname"].hasError('pattern') ? 'Not a valid name' :
        '';
  }
  getFNameErrMes() {
    return this.bookingService.formGroup.controls["fname"].hasError('required') ? 'You must enter a value' :
      this.bookingService.formGroup.controls["fname"].hasError('pattern') ? 'Not a valid name' :
        '';
  }
  // end of the methods used to display the erro messages

  //when the submit button is called this method is executed
  onSubmit() {
    //the schedule is assigned to the booking
    this.bookingService.currentSchedule.subscribe(schedule => this.booking.schedule = schedule);
      this.dob = new Date(this.bookingService.formGroup.controls["dob"].value);
      //the rest of the input given by the user is assigned to the booking
    this.booking.dateOfBirth = this.dob.toLocaleDateString();
    this.booking.driverEmail = this.bookingService.formGroup.controls["email"].value;
    this.booking.driverLastName = this.bookingService.formGroup.controls["lname"].value;
    this.booking.driverFirstName = this.bookingService.formGroup.controls["fname"].value;
    this.booking.driverTelNo = this.bookingService.formGroup.controls["telphoneNo"].value;
    //then the post method is evoked and the booking is passed as aparameter with the vehicle.id
    this.bookingService.createBooking(this.booking, this.vehicle
      .id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.booking = new Booking();
    //if the booking was successful then an alert message is displayed to the user
    alert("The booking is successful.")
    //then this function is called to navigate hte user to the vehicles url
    this.goToAllVehicles();
  }

  goToAllVehicles() {
    this.route.navigate(['/vehicles']);
  }

  
}