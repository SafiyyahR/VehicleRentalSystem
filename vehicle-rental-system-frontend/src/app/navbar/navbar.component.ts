import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  items: Array<{ name: string, link: string }>;
  isAdmin: boolean = false;
  navHome:string;
  constructor() {
    if (this.isAdmin) {
      this.items = [
      {
        name: "BOOKINGS",
        link: "/adbookings"
      },
      {
        name: "VEHICLES",
        link: "/advehicles"
      },
      {
        name: "NOTIFICATIONS",
        link: "/notifications"
      }];
      this.navHome="/adhome"
    } else {
      this.items = [{
        name: "HOME",
        link: "/index"
      },
      {
        name: "BOOKINGS",
        link: "/bookings"
      },
      {
        name: "CONTACT US",
        link: "/contact"
      },
      ];
      this.navHome="/index"
    } 
  }

  ngOnInit() {
  }

}
