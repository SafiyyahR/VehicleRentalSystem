import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Booking } from '../models/booking';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Schedule } from 'src/models/schedule';
import { Vehicle } from 'src/models/vehicle';

@Injectable({
  providedIn: 'root'
})

export class BookingService {
  private availableVehicles = new BehaviorSubject<Vehicle[]>([]);
  currentAvailableVehicles = this.availableVehicles.asObservable();
  private chosenVehicle = new BehaviorSubject<Vehicle>(null);
  currentChosenVehicle = this.chosenVehicle.asObservable();
  private schedule = new BehaviorSubject<Schedule>(null);
  currentSchedule = this.schedule.asObservable();
  nowSchedule: Schedule;
  dateBooked: Date;
  private baseUrl = 'http://localhost:8084/api/bookings';

  constructor(private http: HttpClient) { }

  //forGroup used when asking the driver's information to enter or update
  formGroup: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    fname: new FormControl('', [Validators.required, Validators.pattern(new RegExp("([A-z\s]){3,20}"))]),
    lname: new FormControl('', [Validators.required, Validators.pattern(new RegExp("([A-z\s]){3,20}"))]),
    dob: new FormControl(new Date(), [Validators.required]),
    telphoneNo: new FormControl('', [Validators.required, Validators.pattern("0[1-9]{4}[0-9]{6}")])
  })

  //form group used when login in to manage the bookings
  loginFormGroup: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  })

  //gets all the booking from the api
  getBookingList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  //gets only one booking from the dataabse
  getBooking(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  //creates a booking using the post method
  createBooking(booking: Booking, vehicleId: string): Observable<Object> {
    //the vehicleID is passed as a parameter
    let params: HttpParams = new HttpParams()
      .append('vehicleId', vehicleId)
    return this.http.post(`${this.baseUrl}`, booking, { params: params });
  }

  //the retruns a list of available vehicles during the selected dates
  findAllAvailableVehicles(route: string, pickUpDate: Date, dropOffDate: Date): Observable<any> {
    this.nowSchedule = new Schedule();
    this.dateBooked = new Date();
    this.nowSchedule._id = this.dateBooked.toLocaleString();
    this.nowSchedule.dateBooked = this.dateBooked.toLocaleDateString();
    this.nowSchedule.dropOffDate = dropOffDate.toLocaleDateString();
    this.nowSchedule.pickUpDate = pickUpDate.toLocaleDateString();
    console.log(this.nowSchedule);
    this.schedule.next(this.nowSchedule);
    //the dropoff and pickup dates are passed as requestparams
    let params: HttpParams = new HttpParams()
      .append('pickupdate', this.nowSchedule.pickUpDate)
      .append('dropoffdate', this.nowSchedule.dropOffDate);
    console.log(params);
    return this.http.get(`${this.baseUrl}/${route}`, { params: params });
  }

  //the function used to send the update boooking  
  updateBooking(id: string, booking: Object, vehicleId: string): Observable<Object> {
    let params: HttpParams = new HttpParams()
      .append('vehicleId', vehicleId)
    return this.http.put(`${this.baseUrl}/${id}`, booking, { params: params });
  }

  //updte used to delete a booking
  deleteBooking(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  //assigns the availableVehicles with the list of vehicles
  updateAvailableVehicles(vehicles: Vehicle[]) {
    this.availableVehicles.next(vehicles);
  }

  //update the vehicle attribute
  updateChosenVehicle(vehicle: Vehicle) {
    this.chosenVehicle.next(vehicle);
  }



}
