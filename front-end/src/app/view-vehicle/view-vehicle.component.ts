import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Vehicle } from 'src/models/vehicle';

@Component({
  selector: 'app-view-vehicle',
  templateUrl: './view-vehicle.component.html',
  styleUrls: ['./view-vehicle.component.css']
})
export class ViewVehicleComponent implements OnInit {

  vehicle: Vehicle;
  constructor(
    public dialogRef: MatDialogRef<ViewVehicleComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  //the vehicle is first assigned from the data passed
  ngOnInit(): void {
    this.vehicle = this.data.vehicle;
  }

  //the dialog closes when the user clicks anywhere outside the dialog
  onNoClick(): void {
    this.dialogRef.close();
  }

}
