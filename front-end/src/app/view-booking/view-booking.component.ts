import { Component, OnInit, Inject } from '@angular/core';
import { Booking } from 'src/models/booking';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ViewVehicleComponent } from '../view-vehicle/view-vehicle.component';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {

  booking: Booking;

  constructor(public dialogRef: MatDialogRef<ViewBookingComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any) { }

    //the booking is first assigned from the data passed
  ngOnInit(): void {
    this.booking=this.data.booking;
  }

  //the dialog closes when the user clicks anywhere outside the dialog
  onNoClick(): void {
    this.dialogRef.close();
  }

}
