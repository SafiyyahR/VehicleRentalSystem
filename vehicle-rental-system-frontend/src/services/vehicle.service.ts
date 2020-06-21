import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private baseUrl = 'http://localhost:8084/api/vehicles';
  constructor(private http: HttpClient) { }


  //gets all the vehicles
  getVehicleList(): Observable<any>{
    return this.http.get(`${this.baseUrl}`);
  }

  //gets the vehicle either sorted or filtered
  getSortedVehicleList(params:HttpParams): Observable<any>{
    return this.http.get(`${this.baseUrl}`,{params: params});
  }
}
