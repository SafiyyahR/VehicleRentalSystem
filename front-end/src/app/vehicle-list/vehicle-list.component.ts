import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/models/vehicle';
import { Observable } from 'rxjs';
import { VehicleService } from 'src/service/vehicle.service';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';
import { MatDialog, MatSelectionListChange } from '@angular/material';
import { ViewVehicleComponent } from '../view-vehicle/view-vehicle.component';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {
  types: String[] = ["All", "Car", "Van", "Motorbike"];
  selected: String;
  vehicles: Observable<Vehicle[]>;
  constructor(private vehicleService: VehicleService, private router: Router, public dialog: MatDialog) {
    //initially all filter "all" is chosen 
    this.selected = this.types[0];
  }
  //this function is used to call the uploadVehicleList to retrieve all the vehicles in the system
  ngOnInit() {
    this.uploadVehicleList();
  }

  //used the getVehicleList method to get all the list of vehicles
  uploadVehicleList() {
    this.vehicles = this.vehicleService.getVehicleList();
  }

  //this method is used to sort the vehicles according to make and the highest to lowest of price
  sort(value: string) {
    this.selected = "All";
    //parameters are made and then passed to the function
    let params = new HttpParams()
      .append("sort", value);
    this.vehicles = this.vehicleService.getSortedVehicleList(params);
  }

  //if the filter has been selected then too parameters are made and then sent to the function getSortedVehicleList
  onTypeChange($event: MatSelectionListChange) {
    console.log($event.source._value);
    const value = $event.source._value.toString();
    let params = new HttpParams()
      .append("vehicletype", value);
    this.vehicles = this.vehicleService.getSortedVehicleList(params);
  }

  //this function is called when the user clicks the more button in a vehicle card
  openDialog(vehicle: Vehicle) {
    this.dialog.open(ViewVehicleComponent, {
      width: '700px',
      data: { vehicle: vehicle }
    });
  }


}
