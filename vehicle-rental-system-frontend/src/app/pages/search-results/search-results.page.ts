import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/models/vehicle';
import { Router } from '@angular/router';
import { BookingService } from 'src/services/booking.service';

@Component({
  selector: 'search-results-page',
  templateUrl: './search-results.page.html',
  styleUrls: ['./search-results.page.css']
})
export class SearchResultsPage implements OnInit {
  //declare array of Vehicle
  vehicles: Vehicle[];
  state:any;
  resultsAvailable:boolean;
  constructor(private bookingService: BookingService, private router: Router) {
    //calls the uploads function from the booking Service
    this.state=this.router.getCurrentNavigation().extras.state;
    if(this.state && this.state.vehiclesAdded ){
      this.resultsAvailable=this.state.vehiclesAdded
    }else{
      this.resultsAvailable=false;
    }
    console.log(this.resultsAvailable);
    this.uploadVehicleList();
  }

  ngOnInit() {
  }

  //this method is used to assign the available vehicles to the array declared above
  uploadVehicleList() {
    this.bookingService.currentAvailableVehicles.subscribe(vehicles => {
      this.vehicles = vehicles;
      console.log(this.vehicles);
    });
  }

  //the method is used to navigate to the page used to reserve the vehicle
  reserve(vehicle: Vehicle) {
    //this function is used to update the vehicle to be reserved
    this.bookingService.updateChosenVehicle(vehicle);
    //used to navigate
    this.router.navigate(['reserve']);
  }

  //the method called when the go back button is clicked when there is no available vehicles for the chosen dates
  goBack() {
    this.router.navigate(['index']);
  }

  convertToImg(imgString:any){
    return "data:image/gif;base64,"+imgString;
  }



}
