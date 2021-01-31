import { Component, OnInit, Inject } from '@angular/core';
import { Booking } from 'src/models/booking';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ViewBookingComponent } from '../view-booking/view-booking.component';
import { FormControl, Validators } from '@angular/forms';
import { BookingService } from 'src/service/booking.service';

@Component({
  selector: 'app-update-booking',
  templateUrl: './update-booking.component.html',
  styleUrls: ['./update-booking.component.css']
})
export class UpdateBookingComponent implements OnInit {
  booking: Booking;
  minDateOfBirth = new Date();
  maxDateOfBirth = new Date();
  initDate = new Date();
  dob = new Date();
  constructor(private bookingService: BookingService,public dialogRef: MatDialogRef<ViewBookingComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any) {
      this.ngOnInit();
      this.onReset();
    }

  ngOnInit() {
    this.booking=this.data.booking;
    console.log(this.booking);
  }
  //functions that are called when the user enters something incorrect in the form fields
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

  //this function is called to update the booking and sedn it to the updateBOoking function of the bookingService class
  onSubmit() {
      this.dob = new Date(this.bookingService.formGroup.controls["dob"].value);
    this.booking.dateOfBirth = this.dob.toLocaleDateString();
    this.booking.driverEmail = this.bookingService.formGroup.controls["email"].value;
    this.booking.driverLastName = this.bookingService.formGroup.controls["lname"].value;
    this.booking.driverFirstName = this.bookingService.formGroup.controls["fname"].value;
    this.booking.driverTelNo = this.bookingService.formGroup.controls["telphoneNo"].value;
    const vehicleID = this.booking.vehicle.id;
    this.booking.vehicle=null;
    this.bookingService.updateBooking(this.booking._id,this.booking,vehicleID)
      .subscribe(data => console.log(data), error => console.log(error));
    this.booking = new Booking();
    //once it is successful then the dialog is closed
      this.onNoClick();
  }

  //function used to close the dialog
  onNoClick(): void {
    this.dialogRef.close();
  }

//the function called to reset the form to its previous values
  onReset(){
    this.minDateOfBirth.setFullYear(this.initDate.getFullYear() - 25);
    this.maxDateOfBirth.setFullYear(this.initDate.getFullYear() - 69);
    this.bookingService.formGroup.controls["dob"] = new FormControl(new Date(this.booking.dateOfBirth), [Validators.required]);
    this.bookingService.formGroup.controls["email"] = new FormControl(this.booking.driverEmail, [Validators.required, Validators.email]);
    this.bookingService.formGroup.controls["fname"] = new FormControl(this.booking.driverFirstName,  [Validators.required, Validators.pattern(new RegExp("([A-z\s]){3,20}"))]);
    this.bookingService.formGroup.controls["lname"] = new FormControl(this.booking.driverLastName,  [Validators.required, Validators.pattern(new RegExp("([A-z\s]){3,20}"))]);
    this.bookingService.formGroup.controls["telphoneNo"] = new FormControl(this.booking.driverTelNo, [Validators.required, Validators.pattern("0[1-9]{4}[0-9]{6}")]);
  }

}
