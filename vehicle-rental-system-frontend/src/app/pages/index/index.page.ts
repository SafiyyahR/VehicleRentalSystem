import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'index-page',
  templateUrl: './index.page.html',
  styleUrls: ['./index.page.css']
})
export class IndexPage implements OnInit {
  vehicles: Array<{ img: string, name: string }>;
  constructor() {
    this.vehicles = [
      {
        img: "bicycle.jpg",
        name: "Bicycle"
      }, {
        img: "cabriolet.jpg",
        name: "Cabriolet"
      },
      {
        img: "campervan.jpg",
        name: "Campervan"
      },{
        img: "motorbike.jpg",
        name: "Motorbike"
      },
      {
        img: "nano.jpg",
        name: "Nano Car"
      },
      {
        img: "pickuptruck.jpg",
        name: "Pickup Truck"
      },
      {
        img: "sedan.jpg",
        name: "Sedan"
      },     
      {
        img: "van.jpg",
        name: "Van"
      }
    ];
  }

  ngOnInit() {
  }

}