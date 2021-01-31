import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from 'src/models/booking';
import { BookingService } from 'src/service/booking.service';
import { Router } from '@angular/router';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { ViewBookingComponent } from '../view-booking/view-booking.component';
import { UpdateBookingComponent } from '../update-booking/update-booking.component';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {
  bookings: Booking[];
  constructor(private bookingService: BookingService, private router: Router, public dialog: MatDialog) {}

  //the function is called during the start of the program
  ngOnInit() {
    this.uploadBookingList();
  }

  //this function is used to assign the data to retrieved to an array of booking
  uploadBookingList() {
    this.bookingService.getBookingList().subscribe(data => {
      this.bookings = data;
    });
  }

  //this function is called when the user clicks the view icon
  openDialog(booking: Booking) {
    //the booking is also passed so it can be displayed in the model
    this.dialog.open(ViewBookingComponent, {
      width: '2000px',
      data: {
        booking: booking
      }
    })
  }

  //the booking is deleted using the booking id
  deleteBooking(id: string) {
    this.bookingService.deleteBooking(id)
      .subscribe(
        data => {
          console.log(data);
          this.uploadBookingList();
          //user is informed about the deletion of the record
          alert("The booking has been deleted.")
        },
        error => console.log(error));
  }

  //the booking to be updated is updated using a form in a modal
  updateBooking(booking: Booking) {
    this.dialog.open(UpdateBookingComponent, {
      width: '60%',
      data: {
        booking: booking
      }
    })
  }

}
