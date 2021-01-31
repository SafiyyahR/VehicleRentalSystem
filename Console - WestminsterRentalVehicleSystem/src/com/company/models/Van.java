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

public class Van extends Vehicle{
    private int noOfSeats;
    private int noOfLuggages;

    //default constructor
    public Van(){}

    //constructor
    public Van(String id, String plateNumber, String make, String model, String colour, int registrationYear, double rentalPerHour, boolean isManual, int mileage, int engineDisplacement, int noOfSeats, int noOfLuggages) {
        super(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement);
        this.noOfSeats = noOfSeats;
        this.noOfLuggages = noOfLuggages;
    }

    //getters and setters
    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getNoOfLuggages() {
        return noOfLuggages;
    }

    public void setNoOfLuggages(int noOfLuggages) {
        this.noOfLuggages = noOfLuggages;
    }

    //toString method to return the details of the van as a string
    @Override
    public String toString() {
        return "Van[" +
                super.toString()+
                "\n Seating Capacity" + noOfSeats +
                "\n No. of Luggage" + noOfLuggages +
                ']';
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Van)) return false;
        if (!super.equals(o)) return false;
        Van van = (Van) o;
        return getNoOfSeats() == van.getNoOfSeats() &&
                getNoOfLuggages() == van.getNoOfLuggages();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNoOfSeats(), getNoOfLuggages());
    }

    //this method is used to print the vehicle type and plate Number
    public String print(){
        return "Van: "+ this.getPlateNumber();
    }
}
