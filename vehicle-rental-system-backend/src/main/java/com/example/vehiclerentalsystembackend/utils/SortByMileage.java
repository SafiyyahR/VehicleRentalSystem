package com.example.vehiclerentalsystembackend.utils;

import com.example.vehiclerentalsystembackend.model.Vehicle;

import java.util.Comparator;

public class SortByMileage implements Comparator<Vehicle> {

    //this function is used to help in the sort of the vehicleList using the rentalPerHour attribute
    @Override
    public int compare(Vehicle vehicle, Vehicle vehicle1) {
       return Long.compare(vehicle.getMileage(),vehicle1.getMileage());
    }

}
