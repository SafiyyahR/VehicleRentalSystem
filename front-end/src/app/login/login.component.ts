import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/service/booking.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  constructor(private bookingService: BookingService, private route: Router) { }

  ngOnInit() {
  }

  getUserErrMes() {
    return this.bookingService.loginFormGroup.controls["username"].hasError('required') ? 'You must enter a value' : '';
  }

  getPasswordErrMes() {
    return this.bookingService.loginFormGroup.controls["password"].hasError('required') ? 'You must enter a value' : '';
  }

  //this method is called when the user clicks the login button the method checks if the user is the admin and only then are they navigated to the bookings page
  onSubmit(){
    this.username = this.bookingService.loginFormGroup.controls['username'].value;
    this.password = this.bookingService.loginFormGroup.controls['password'].value;
    if(this.username!="Admin" || this.password!="Admin@iit"){
      alert("Password or Username is incorrect")
    }else{
      this.goToAllBookings();
    }
  }

  goToAllBookings() {
    this.route.navigate(['/bookings']);
  }
}
