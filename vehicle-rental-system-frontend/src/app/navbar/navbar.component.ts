import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  items: Array<{ name: string, link: string }>;
  constructor() {
    this.items = [{
      name: "Home",
      link: ""
    },
    {
      name: "Bookings",
      link: "/bookings"
    },
    {
      name: "Contact Us",
      link: "/contact"
    },
    {
      name: "Signup",
      link: "/signup"
    }];
  }

  ngOnInit() {
  }

}
