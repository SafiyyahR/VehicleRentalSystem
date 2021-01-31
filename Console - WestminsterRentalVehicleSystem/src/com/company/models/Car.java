/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A subclass of Vehicle type
*/
package com.company.models;

import java.util.Objects;

public class Car extends Vehicle {
    //Car is a subtype of Vehicle
    private int noOfDoors;
    private boolean hasAC;
    private int noOfSeats;

    //default constructor
    public Car(){}

    //constructor
    public Car(String id, String plateNumber, String make, String model, String colour, int registrationYear, double rentalPerHour, boolean isManual, int mileage, int engineDisplacement, int noOfDoors, boolean hasAC, int noOfSeats) {
        super(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement);
        this.noOfDoors = noOfDoors;
        this.hasAC = hasAC;
        this.noOfSeats = noOfSeats;
    }

    //getters and setters
    public void setNoOfDoors(int noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public boolean getHasAC() {
        return hasAC;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    //toString method to return the details of the Car as a string
    @Override
    public String toString() {
        return "Car[" +
                super.toString() +
                "\n No of Doors: " + noOfDoors +
                "\n AC: " + hasAC +
                "\n Seating Capacity: " + noOfSeats +
                ']';
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return getNoOfDoors() == car.getNoOfDoors() &&
                getHasAC() == car.getHasAC() &&
                getNoOfSeats() == car.getNoOfSeats();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNoOfDoors(), getHasAC(), getNoOfSeats());
    }

    //this method is used to print the vehicle type and plate Number
    public String print() {
        return "Car: " + this.getPlateNumber();
    }
}
